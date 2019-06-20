package org.colorcoding.ibas.bobas.ownership.post;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.colorcoding.ibas.bobas.common.ConditionRelationship;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.data.ArrayList;
import org.colorcoding.ibas.bobas.data.emAuthoriseType;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.expression.JudmentOperationException;
import org.colorcoding.ibas.bobas.i18n.I18N;
import org.colorcoding.ibas.bobas.mapping.BusinessObjectUnit;
import org.colorcoding.ibas.bobas.message.Logger;
import org.colorcoding.ibas.bobas.message.MessageLevel;
import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.post.OrganizationManager;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;
import org.colorcoding.ibas.bobas.ownership.IOwnershipJudger;
import org.colorcoding.ibas.bobas.ownership.UnauthorizedException;
import org.colorcoding.ibas.bobas.ownership.post.matching.IMatchingStrategy;
import org.colorcoding.ibas.bobas.repository.InvalidTokenException;
import org.colorcoding.ibas.initialfantasy.bo.bofiltering.BOFiltering;
import org.colorcoding.ibas.initialfantasy.bo.bofiltering.IBOFiltering;
import org.colorcoding.ibas.initialfantasy.bo.shell.User;
import org.colorcoding.ibas.initialfantasy.data.emFilteringType;
import org.colorcoding.ibas.organizedposts.MyConfiguration;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnership;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnershipItem;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnershipItems;
import org.colorcoding.ibas.organizedposts.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedposts.bo.ownership.OwnershipItem;
import org.colorcoding.ibas.organizedposts.data.emOwnershipSign;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;
import org.colorcoding.ibas.organizedposts.repository.IBORepositoryOrganizedPostsApp;

/**
 * 权限裁判
 *
 * @author Niuren.Zhu
 */
public class OwnershipJudger implements IOwnershipJudger {
	public static final String MSG_OWNERSHIP_JUDGER_DATA_FILTERED = "judger: position [%s] filtered data [%s].";
	public static final String MSG_OWNERSHIP_JUDGER_NOT_ALLOW_READ = "judger: finally, user [%s] can't read data [%s].";
	public static final String MSG_OWNERSHIP_JUDGER_ALLOW_READ = "judger: finally, user [%s] can read data [%s].";
	public static final String MSG_OWNERSHIP_JUDGER_NOT_ALLOW_SAVE = "judger: finally,user [%s] can't save data [%s].";
	public static final String MSG_OWNERSHIP_JUDGER_ALLOW_SAVE = "judger: finally,user [%s] can save data [%s].";
	public static final String MSG_OWNERSHIP_JUDGER_JUDGING = "judger: judging [%s],priority is [%s]";
	public static final String MSG_OWNERSHIP_JUDGER_JUDGED = "judger: judged [%s],result is [%s]";
	public static final String MSG_OWNERSHIP_JUDGER_SKIP = "judger: skip judge [%s/%s]";
	public static final String MSG_OWNERSHIP_JUDGER_CURRENT_STATE = "judger: current judged state is [%s]";

	public OwnershipJudger() {
		// 职位数据过滤，优先可用
		this.setRoleDataFirstAvailable(
				MyConfiguration.getConfigValue(MyConfiguration.CONFIG_ITEM_ROLE_DATA_FIRST_AVAILABLE, true));
	}

	private boolean roleDataFirstAvailable;

	public boolean isRoleDataFirstAvailable() {
		return roleDataFirstAvailable;
	}

	public void setRoleDataFirstAvailable(boolean roleDataFirstAvailable) {
		this.roleDataFirstAvailable = roleDataFirstAvailable;
	}

	protected OrganizationManager getOrganizationManager() {
		return (OrganizationManager) OrganizationFactory.create().createManager();
	}

	private BORepositoryOrganizedPosts repository;

	private IBORepositoryOrganizedPostsApp createRepository() {
		if (repository == null) {
			try {
				repository = new BORepositoryOrganizedPosts();
				repository.setUserToken(OrganizationFactory.SYSTEM_USER.getToken());
			} catch (InvalidTokenException e) {
				throw new RuntimeException(e);
			}
		}
		return repository;
	}

	private HashMap<String, IOwnership> ownerships = new HashMap<>();

	/**
	 * 获取所有权配置
	 *
	 * @param boCode 业务对象
	 * @param user   用户编码
	 * @return
	 */
	protected IOwnership getOwnership(String boCode, String user) {
		String key = String.format("%s/%s", boCode, user);
		// 缓存中查询数据
		if (ownerships.containsKey(key)) {
			return ownerships.get(key);
		}
		// 构建查询条件
		ICriteria criteria = new Criteria();
		ICondition condition = criteria.getConditions().create();
		condition.setAlias(Ownership.PROPERTY_BOCODE.getName());
		condition.setValue(boCode);
		condition = criteria.getConditions().create();
		condition.setAlias(Ownership.PROPERTY_ACTIVATED.getName());
		condition.setValue(emYesNo.YES);
		condition = criteria.getConditions().create();
		condition.setAlias(Ownership.PROPERTY_USERCODE.getName());
		condition.setValue(user);

		IBORepositoryOrganizedPostsApp boRepository = this.createRepository();
		IOperationResult<?> operationResult = boRepository.fetchOwnership(criteria);
		IOwnership ownership = (IOwnership) operationResult.getResultObjects().firstOrDefault();
		if (ownership == null) { // 优先取BO此User个性权限
			condition.setValue(""); // 若取不到,取该BO通用权限
			operationResult = boRepository.fetchOwnership(criteria);
			ownership = (IOwnership) operationResult.getResultObjects().firstOrDefault();
		}
		ownerships.put(key, ownership);
		return ownership;
	}

	private HashMap<String, IBOFiltering> boFilterings = new HashMap<>();

	/**
	 * 获取所有权配置
	 *
	 * @param boCode    业务对象
	 * @param positions 职位数组
	 * @return
	 */
	protected IBOFiltering[] getBOFilterings(String boCode, String[] positions) {
		ArrayList<IBOFiltering> filterings = new ArrayList<>();
		ArrayList<String> doPositions = new ArrayList<>();
		for (String role : positions) {
			String key = String.format("%s/%s", boCode, role);
			if (boFilterings.containsKey(key)) {
				filterings.add(boFilterings.get(key));
			} else {
				// 不存在的职位权限
				doPositions.add(role);
			}
		}
		// 构建查询条件， (BOCode = 'XXXX' AND Activated = 'Y')
		// AND (Role = 'A' OR Role = 'B')
		if (doPositions.size() > 0) {
			// 存在未缓存的职位权限
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setBracketOpen(1);
			condition.setAlias(BOFiltering.PROPERTY_BOCODE.getName());
			condition.setValue(boCode);
			condition = criteria.getConditions().create();
			condition.setAlias(BOFiltering.PROPERTY_ACTIVATED.getName());
			condition.setValue(emYesNo.YES);
			condition.setBracketClose(1);
			for (int i = 0; i < doPositions.size(); i++) {
				condition = criteria.getConditions().create();
				condition.setAlias(BOFiltering.PROPERTY_ROLECODE.getName());
				condition.setValue(doPositions.get(i));
				condition.setRelationship(ConditionRelationship.OR);
				if (i == 0) {
					// first.
					condition.setRelationship(ConditionRelationship.AND);
					condition.setBracketOpen(1);
				}
				if (i == doPositions.size() - 1) {
					// last.
					condition.setBracketClose(1);
				}
			}
			IBORepositoryOrganizedPostsApp boRepository = this.createRepository();
			IOperationResult<?> operationResult = boRepository.fetchBOFiltering(criteria);
			for (IBOFiltering filtering : operationResult.getResultObjects().toArray(new IBOFiltering[] {})) {
				String key = String.format("%s/%s", filtering.getBOCode(), filtering.getRoleCode());
				boFilterings.put(key, filtering);// 缓存数据
				filterings.add(filtering);// 返回数据
			}
		}
		return filterings.toArray(new IBOFiltering[] {});
	}

	/**
	 * 过滤数据
	 * <p>
	 * 注意，配置的条件中，任意一个不满足则不满足
	 *
	 * @param bo        数据
	 * @param positions 职位
	 * @return true, 被过滤；false,未被过滤
	 */
	protected boolean filtering(IDataOwnership bo, String[] positions) {
		if (bo != null && positions != null && positions.length > 0) {
			IBOFiltering[] filterings = this.getBOFilterings(bo.getObjectCode(), positions);
			boolean availableMatched = false; // 匹配到可见过滤条件
			boolean unavailableMatched = false; // 匹配到不可见过滤条件
			String roleCode = null;
			for (IBOFiltering filtering : filterings) {
				try {
					BOFilteringJudgmentLinks judgmentLinks = new BOFilteringJudgmentLinks();
					judgmentLinks.parsingConditions(filtering.getBOFilteringConditions());
					boolean done = judgmentLinks.judge(bo);
					if (done) {
						// 符合条件
						if (filtering.getFilteringType() == emFilteringType.AVAILABLE) {
							// 匹配到可见
							availableMatched = true;
						} else if (filtering.getFilteringType() == emFilteringType.UNAVAILABLE) {
							// 匹配到不可见
							unavailableMatched = true;
						}
					} else {
						// 不符合条件
						if (filtering.getFilteringType() == emFilteringType.AVAILABLE) {
							// 匹配到不可见
							unavailableMatched = true;
						} else if (filtering.getFilteringType() == emFilteringType.UNAVAILABLE) {
							// 匹配到可见
							availableMatched = true;
						}
						roleCode = filtering.getRoleCode();
						Logger.log(MSG_OWNERSHIP_JUDGER_DATA_FILTERED, roleCode, bo.toString());
					}
				} catch (JudmentOperationException e) {
					Logger.log(e);
				}
			}
			// 匹配到条件
			if (availableMatched | unavailableMatched) {
				if (availableMatched & unavailableMatched) {
					// 可见和不可见条件都匹配到,按照默认优先级
					// 优先可见则不过滤
					return !this.isRoleDataFirstAvailable();
				} else {
					// 可见和不可见条件有且只有一个匹配到
					// 若不可见为真,则过滤;若为假,则不过滤
					return unavailableMatched;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean canRead(IDataOwnership bo, IUser user) {
		boolean status = false;
		emAuthoriseType authorise = getAuthorise(bo, user);
		if (authorise == emAuthoriseType.NONE) {
			// 不允许读取数据
			Logger.log(MSG_OWNERSHIP_JUDGER_NOT_ALLOW_READ, user.toString(), bo.toString());
			status = false;
		} else {
			// 允许读取数据
			Logger.log(MSG_OWNERSHIP_JUDGER_ALLOW_READ, user.toString(), bo.toString());
			status = true;
		}
		// 判断数据是否设置过滤条件
		if (status) {
			// 数据被设置了过滤条件
			String[] roles = this.getOrganizationManager().getRoles(user);
			status = !this.filtering(bo, roles);
		}
		return status;
	}

	@Override
	public boolean canRead(IDataOwnership bo, IUser user, boolean throwError) throws UnauthorizedException {
		boolean status = this.canRead(bo, user);
		if (throwError && !status) {
			throw new UnauthorizedException(
					I18N.prop("msg_op_to_fetch_bo_unauthorized", bo.getClass().getSimpleName()));
		}
		return status;
	}

	@Override
	public boolean canSave(IDataOwnership bo, IUser user) {
		emAuthoriseType authorise = getAuthorise(bo, user);
		if (authorise == emAuthoriseType.ALL) {
			// 允许保存数据
			Logger.log(MSG_OWNERSHIP_JUDGER_ALLOW_SAVE, user.toString(), bo.toString());
			return true;
		} else {
			// 不允许保存数据
			Logger.log(MSG_OWNERSHIP_JUDGER_NOT_ALLOW_SAVE, user.toString(), bo.toString());
			return false;
		}
	}

	@Override
	public boolean canSave(IDataOwnership bo, IUser user, boolean throwError) throws UnauthorizedException {
		boolean status = this.canSave(bo, user);
		if (throwError && !status) {
			throw new UnauthorizedException(I18N.prop("msg_op_to_save_bo_unauthorized", bo.getClass().getSimpleName()));
		}
		return status;
	}

	/**
	 * 获取当前用户对当前BO的数据权限
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	private emAuthoriseType getAuthorise(IDataOwnership bo, IUser user) {
		emAuthoriseType authorise = emAuthoriseType.ALL;
		if (!isRoleDataFirstAvailable()) {
			// 优先没有权限
			authorise = emAuthoriseType.NONE;
		}
		// 获取数据所有者
		org.colorcoding.ibas.bobas.organization.IUser owner = this.getOrganizationManager().getUser(bo.getDataOwner());
		if (owner != null && owner instanceof User && user != null && bo != null) {
			if (user != OrganizationFactory.SYSTEM_USER) {// 系统用户，不进行权限处理
				// 操作用户是否有权限读取数据
				IOwnership ownership = this.getOwnership(bo.getObjectCode(), ((User) owner).getCode());
				if (ownership != null) {
					List<IOwnershipItem> ownershipItems = sortOwnershipItems(ownership.getOwnershipItems());
					authorise = ownership.getDefaultPermission(); // 获取默认权限
					int currentPriority = Integer.MIN_VALUE;
					for (IOwnershipItem ownershipItem : ownershipItems) {
						if (currentPriority == ownershipItem.getPriority() && authorise == emAuthoriseType.ALL
								&& this.isRoleDataFirstAvailable()) {
							Logger.log(MessageLevel.INFO, MSG_OWNERSHIP_JUDGER_SKIP, ownershipItem.getItemSign(),
									ownershipItem.getItemDescription());
							continue; // 已取得最大权限,当前优先级不需再继续判断
						}
						IMatchingStrategy matchingStrategy = MatchingStrategyFactory.create()
								.createMatchingStrategy(ownershipItem.getItemSign());
						if (matchingStrategy == null) {
							continue;
						}
						matchingStrategy.setParameter(ownershipItem.getParameter()); // 设置参数
						Logger.log(MessageLevel.INFO, MSG_OWNERSHIP_JUDGER_JUDGING,
								matchingStrategy.getClass().getName(), ownershipItem.getPriority());
						boolean done = matchingStrategy.match(bo, user);
						Logger.log(MessageLevel.INFO, MSG_OWNERSHIP_JUDGER_JUDGED,
								matchingStrategy.getClass().getName(), done);
						if (done) {
							if (currentPriority == ownershipItem.getPriority()) {
								authorise = computeAuthoriseSet(authorise, ownershipItem.getPermission());
							} else {
								currentPriority = ownershipItem.getPriority();
								authorise = ownershipItem.getPermission();
							}
						}
						Logger.log(MessageLevel.INFO, MSG_OWNERSHIP_JUDGER_CURRENT_STATE, authorise);
					}
				}
			}
		}
		return authorise;
	}

	/**
	 * 对访问权限项进行排序,对没有指定优先级的使用默认优先级
	 *
	 * @param ownershipItems
	 * @return
	 */
	private List<IOwnershipItem> sortOwnershipItems(IOwnershipItems ownershipItems) {
		List<IOwnershipItem> list = new LinkedList<IOwnershipItem>();
		list.addAll(ownershipItems);
		if (ownershipItems.firstOrDefault(item -> item.getItemSign() == emOwnershipSign.APPROVAL) == null) {
			// 默认添加审批数据所有权-全部
			IOwnershipItem item = new OwnershipItem();
			item.setItemSign(emOwnershipSign.APPROVAL);
			item.setPermission(emAuthoriseType.ALL);
			list.add(item);
		}
		list.forEach(new Consumer<IOwnershipItem>() {
			@Override
			public void accept(IOwnershipItem ownershipItem) {
				// 更新优先级
				IMatchingStrategy matchingStrategy = MatchingStrategyFactory.create()
						.createMatchingStrategy(ownershipItem.getItemSign());
				if (ownershipItem.getPriority() == 0) {
					ownershipItem.setPriority(matchingStrategy.getPriority());
				}
			}
		});
		list.sort(new Comparator<IOwnershipItem>() {
			@Override
			public int compare(IOwnershipItem o1, IOwnershipItem o2) {
				// TODO: 对访问权限项进行排序,对没有指定优先级的使用默认优先级
				return o1.getPriority() - o2.getPriority();
			}
		});
		return list;
	}

	/**
	 * 计算两个权限的并集
	 *
	 * @return
	 */
	private emAuthoriseType computeAuthoriseSet(emAuthoriseType original, emAuthoriseType additional) {
		// TODO: 计算两个权限的并集
		emAuthoriseType result;
		if (isRoleDataFirstAvailable()) {
			// 角色数据过滤，优先可用
			if (original == emAuthoriseType.NONE && additional == emAuthoriseType.NONE) {
				result = emAuthoriseType.NONE;
			} else if (original == emAuthoriseType.ALL || additional == emAuthoriseType.ALL) {
				result = emAuthoriseType.ALL;
			} else {
				result = emAuthoriseType.READ;
			}
		} else {
			// 角色数据过滤，优先不可用
			if (original == emAuthoriseType.NONE || additional == emAuthoriseType.NONE) {
				result = emAuthoriseType.NONE;
			} else if (original == emAuthoriseType.ALL || additional == emAuthoriseType.ALL) {
				result = emAuthoriseType.ALL;
			} else {
				result = emAuthoriseType.READ;
			}
		}
		return result;
	}

	@Override
	public ICriteria filterCriteria(BusinessObjectUnit boUnit, IUser user) {
		return null;
	}
}
