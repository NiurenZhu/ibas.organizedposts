package org.colorcoding.ibas.bobas.organization.post;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.common.ConditionOperation;
import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.ICondition;
import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.data.DateTime;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.message.Logger;
import org.colorcoding.ibas.bobas.organization.IOrganizationManager;
import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.util.EncryptMD5;
import org.colorcoding.ibas.initialfantasy.bo.shell.User;
import org.colorcoding.ibas.organizedposts.MyConfiguration;
import org.colorcoding.ibas.organizedposts.bo.post.IPost;
import org.colorcoding.ibas.organizedposts.bo.post.Post;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;
import org.colorcoding.ibas.organizedposts.repository.IBORepositoryOrganizedPostsApp;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "OrganizationManager")
@XmlRootElement(name = "OrganizationManager")
public class OrganizationManager implements IOrganizationManager {
	@Override
	public void initialize() {
		this.initialize(false);
	}

	public synchronized void initialize(boolean force) {
		this.undistributedUsers = null;
		this.organizations = null;
		this.repository = null;
		this.load(force);
	}

	protected void load(boolean force) {
		// 尝试从缓存中加载数据
		JAXBContext context = null;
		File file = new File(this.getCacheFilePath());
		if (file.isFile() && file.exists() && !force) {
			Long fileTime = file.lastModified();
			Long nowTime = System.currentTimeMillis();
			int freshTime = MyConfiguration.getConfigValue(
					MyConfiguration.CONFIG_ITEM_ORGANIZATION_MANAGER_EXPIRY_VALUE,
					MyConfiguration.isDebugMode() ? 180 : 600);
			if ((nowTime - fileTime) <= (freshTime * 1000)) {
				// 缓存文件有效期内
				try (FileInputStream fileStream = new FileInputStream(file)) {
					context = JAXBContext.newInstance(OrganizationManager.class);
					Unmarshaller unmarshaller = context.createUnmarshaller();
					OrganizationManager manager = (OrganizationManager) unmarshaller.unmarshal(fileStream);
					this.organizations = manager.organizations;
					this.undistributedUsers = manager.undistributedUsers;
					// 有效的数据
					Logger.log(String.format("organization: read the cached data, [%s].", file.getPath()));
					return;// 退出
				} catch (JAXBException | IOException e) {
					Logger.log(e);
				}
			}
		}
		this.undistributedUsers = this.loadUsers();// 初始化未分配组织的用户(加载所有用户)
		List<Organization> organizations = this.load(-1);// 加载根
		this.organizations = organizations.toArray(new Organization[] {});
		// 移除已分配组织的用户
		for (IUser user : this.getUsers()) {
			this.getUndistributedUsers().removeIf(new Predicate<IUser>() {
				@Override
				public boolean test(IUser tmpUser) {
					return tmpUser.getId() == user.getId();
				}
			});
		}
		Logger.log(String.format("organization: read data in the database."));
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			context = JAXBContext.newInstance(OrganizationManager.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			try (FileOutputStream out = new FileOutputStream(file, false)) {
				marshaller.marshal(this, out);
				out.flush();
			}
			Logger.log(String.format("organization: cache data in file, [%s].", file.getPath()));
		} catch (JAXBException | IOException e) {
			Logger.log(e);
		}
	}

	protected List<Organization> load(Integer node) {
		List<Organization> results = new ArrayList<>();
		try {
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(Post.PROPERTY_BELONGING.getName());
			condition.setValue(node);
			condition = criteria.getConditions().create();
			condition.setAlias(Post.PROPERTY_VALIDDATE.getName());
			condition.setOperation(ConditionOperation.LESS_EQUAL);
			condition.setValue(DateTime.getToday());
			condition = criteria.getConditions().create();
			condition.setAlias(Post.PROPERTY_INVALIDDATE.getName());
			condition.setOperation(ConditionOperation.GRATER_EQUAL);
			condition.setValue(DateTime.getToday());
			IBORepositoryOrganizedPostsApp boRepository = this.createRepository();
			IOperationResult<IPost> operationResult = boRepository.fetchPost(criteria);
			if (operationResult.getError() != null) {
				throw operationResult.getError();
			}
			if (operationResult.getResultCode() != 0) {
				throw new Exception(operationResult.getMessage());
			}
			for (IPost orgItem : operationResult.getResultObjects()) {
				Organization organization = new Organization();
				organization.setCode(orgItem.getCode());
				User manager = this.createUser(orgItem.getUserCode());
				if (manager != null) {
					manager.setBelong(orgItem.getPosition());
				}
				organization.setManager(manager);
				// 加载子项
				organization.setOrganizations(this.load(orgItem.getObjectKey()));
				for (Organization subOrganization : organization.getOrganizations()) {
					IUser user = subOrganization.getManager();
					if (user != null) {
						organization.getMembers().add(user);
					}
				}
				results.add(organization);
			}
		} catch (Exception e) {
			Logger.log(e);
		}
		return results;
	}

	protected ArrayList<IUser> loadUsers() {
		ArrayList<IUser> results = new ArrayList<>();
		try {
			ICriteria criteria = new Criteria();
			ICondition condition = criteria.getConditions().create();
			condition.setAlias(org.colorcoding.ibas.initialfantasy.bo.organization.User.PROPERTY_ACTIVATED.getName());
			condition.setValue(emYesNo.YES);
			IBORepositoryOrganizedPostsApp boRepository = this.createRepository();
			IOperationResult<org.colorcoding.ibas.initialfantasy.bo.organization.IUser> operationResult = boRepository
					.fetchUser(criteria);
			if (operationResult.getError() != null) {
				throw operationResult.getError();
			}
			for (org.colorcoding.ibas.initialfantasy.bo.organization.IUser item : operationResult.getResultObjects()) {
				results.add(User.create(item));
			}
		} catch (Exception e) {
			Logger.log(e);
		}
		return results;
	}

	@Override
	public IUser getUser(String token) {
		// 系统用户
		if (OrganizationFactory.SYSTEM_USER.getToken().equals(token)) {
			return OrganizationFactory.SYSTEM_USER;
		}
		// 已分配组织的操作用户
		for (IUser user : this.getUsers()) {
			if (user.getToken().equals(token)) {
				return user;
			}
		}
		// 未分配组织的操作用户
		for (IUser user : this.getUndistributedUsers()) {
			if (user.getToken().equals(token)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public IUser getUser(int id) {
		// 系统用户
		if (OrganizationFactory.SYSTEM_USER.getId() == id) {
			return OrganizationFactory.SYSTEM_USER;
		}
		// 已分配组织的操作用户
		for (IUser user : this.getUsers()) {
			if (user.getId() == id) {
				return user;
			}
		}
		// 未分配组织的用户
		for (IUser user : this.getUndistributedUsers()) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void register(IUser user) {
		if (user == null) {
			return;
		}
		for (int i = 0; i < this.getUndistributedUsers().size(); i++) {
			IUser item = this.getUndistributedUsers().get(i);
			if (item == null) {
				continue;
			}
			if (item.getId() == user.getId()) {
				this.getUndistributedUsers().set(i, user);
				return;
			}
		}
		this.getUndistributedUsers().add(user);
	}

	@Override
	public String[] getRoles(IUser user) {
		List<String> roles = new ArrayList<>();
		for (IUser item : this.getUsers()) {
			if (item.getId() == user.getId()) {
				if (roles.contains(item.getBelong())) {
					continue;
				}
				roles.add(item.getBelong());
			}
		}
		return roles.toArray(new String[] {});
	}

	protected String getCacheFilePath() {
		// 当前数据库地址和数据库名称作为锁标记
		String dbServer = MyConfiguration.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_SERVER);
		String dbName = MyConfiguration.getConfigValue("Master" + MyConfiguration.CONFIG_ITEM_DB_NAME);
		String sign = EncryptMD5.md5(dbServer, dbName);
		return MyConfiguration.getTempFolder() + File.separator + "~post_cache_" + sign + ".tmp";
	}

	private BORepositoryOrganizedPosts repository;

	private IBORepositoryOrganizedPostsApp createRepository() {
		if (repository == null) {
			try {
				repository = new BORepositoryOrganizedPosts();
				repository.setUserToken(OrganizationFactory.SYSTEM_USER.getToken());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return repository;
	}

	private User createUser(String code) {
		if (code == null || code.isEmpty()) {
			return null;
		}
		for (int i = 0; i < this.getUndistributedUsers().size(); i++) {
			IUser item = this.getUndistributedUsers().get(i);
			if (item == null) {
			}
			if (item instanceof User) {
				if (((User) item).getCode().equals(code)) {
					return (User) item;
				}
			}
		}
		ICriteria criteria = new Criteria();
		ICondition condition = criteria.getConditions().create();
		condition.setAlias(org.colorcoding.ibas.initialfantasy.bo.organization.User.PROPERTY_CODE.getName());
		condition.setValue(code);
		condition = criteria.getConditions().create();
		condition.setAlias(org.colorcoding.ibas.initialfantasy.bo.organization.User.PROPERTY_ACTIVATED.getName());
		condition.setValue(emYesNo.YES);
		IBORepositoryOrganizedPostsApp boRepository = this.createRepository();
		IOperationResult<org.colorcoding.ibas.initialfantasy.bo.organization.IUser> operationResult = boRepository
				.fetchUser(criteria);
		if (operationResult.getError() == null && operationResult.getResultCode() == 0) {
			org.colorcoding.ibas.initialfantasy.bo.organization.IUser tmpUser = operationResult.getResultObjects()
					.firstOrDefault();
			if (tmpUser != null) {
				User user = User.create(tmpUser);
				return user;
			}
		}
		return null;
	}

	@XmlElementWrapper(name = "undistributedUsers")
	@XmlElement(name = "undistributedUser", type = User.class)
	private ArrayList<IUser> undistributedUsers;

	protected ArrayList<IUser> getUndistributedUsers() {
		if (undistributedUsers == null) {
			undistributedUsers = new ArrayList<>();
		}
		return this.undistributedUsers;
	}

	@XmlElementWrapper(name = "organizations")
	@XmlElement(name = "organization", type = Organization.class)
	private Organization[] organizations;

	/**
	 * 获取全部组织
	 *
	 * @param flat true，扁平的；false，仅根组织
	 * @return
	 */
	protected Organization[] getOrganizations(boolean flat) {
		if (this.organizations == null) {
			return new Organization[] {};
		}
		if (!flat) {
			return this.organizations;
		}
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : this.organizations) {
			orgs.addAll(this.getOrganizations(org));
		}
		return orgs.toArray(new Organization[] {});
	}

	protected List<Organization> getOrganizations(Organization organization) {
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : organization.getOrganizations()) {
			orgs.addAll(this.getOrganizations(org));
		}
		orgs.add(organization);
		return orgs;
	}

	/**
	 * 获取用户所属的组织
	 *
	 * @param user 用户
	 * @return
	 */
	public Organization[] getOrganizations(IUser user) {
		ArrayList<Organization> orgs = new ArrayList<>();
		for (Organization org : this.getOrganizations(true)) {
			if (org.getManager().getId() == user.getId()) {
				orgs.add(org);
			}
			for (IUser item : org.getMembers()) {
				if (item.getId() == user.getId()) {
					orgs.add(org);
					break;
				}
			}
		}
		return orgs.toArray(new Organization[] {});
	}

	private List<IUser> getUsers(Organization organization) {
		ArrayList<IUser> users = new ArrayList<>();
		for (Organization org : organization.getOrganizations()) {
			users.addAll(this.getUsers(org));
		}
		users.addAll(organization.getMembers());
		users.add(organization.getManager());
		return users;
	}

	private IUser[] getUsers() {
		ArrayList<IUser> users = new ArrayList<>();
		for (Organization org : this.getOrganizations(false)) {
			users.addAll(this.getUsers(org));
		}
		return users.toArray(new IUser[] {});
	}

	/**
	 * 判断两者组织关系
	 * <p>
	 * someone是user的什么
	 *
	 * @param user
	 * @param someone
	 * @return 所属组织的manager即为领导，若本身为manager，则上级组织的manager为领导。所属同一组织且同角色即为同事， 领导没有同事
	 *         作为manager所属组织中的成员即为下属，而自身为上级组织manager的下属。
	 */
	public OrganizationalRelationship getRelationship(IUser user, IUser someone) {
		if (user == null || someone == null) {
			return OrganizationalRelationship.NONE;
		}
		if (user.getId() == OrganizationFactory.UNKNOWN_USER.getId()
				|| someone.getId() == OrganizationFactory.UNKNOWN_USER.getId()) {
			return OrganizationalRelationship.NONE;
		}
		// 获取组织
		Organization[] userOrg = this.getOrganizations(user);
		if (userOrg == null || userOrg.length == 0) {
			return OrganizationalRelationship.NONE;
		}
		Organization[] someOrg = this.getOrganizations(someone);
		if (someOrg == null || someOrg.length == 0) {
			return OrganizationalRelationship.NONE;
		}
		// 所属组织的manager即为领导，若本身为manager，则上级组织的manager为领导
		for (Organization organization : someOrg) {
			// someone是领导
			if (organization.getManager().getId() == someone.getId()) {
				for (IUser item : organization.getMembers()) {
					if (item.getId() == user.getId()) {
						return OrganizationalRelationship.LEADER;
					}
				}
				// 上级组织的manager为领导
				if (this.isSubordinateManagers(organization, user)) {
					return OrganizationalRelationship.LEADER;
				}
			}
		}
		// 所属同一组织即为同事，领导没有同事
		for (Organization organization : someOrg) {
			for (IUser item : organization.getMembers()) {
				if (item.getId() == user.getId()) {
					return OrganizationalRelationship.COLLEAGUES;
				}

			}
		}
		// 作为manager所属组织中的成员即为下属，而自身为上级组织manager的下属
		for (Organization organization : userOrg) {
			// user是领导
			if (organization.getManager().getId() == user.getId()) {
				for (IUser item : organization.getMembers()) {
					if (item.getId() == someone.getId()) {
						return OrganizationalRelationship.SUBORDINATE;
					}
				}
				// 上级组织manager的下属
				if (this.isSubordinateManagers(organization, someone)) {
					return OrganizationalRelationship.SUBORDINATE;
				}
			}
		}
		// 没有解析出来的均为无关系
		return OrganizationalRelationship.NONE;
	}

	/**
	 * 判断用户是否为下级组织manger
	 *
	 * @param organization 组织
	 * @param user         用户
	 * @return
	 */
	private boolean isSubordinateManagers(Organization organization, IUser user) {
		if (organization.getManager().getId() == user.getId()) {
			return true;
		}
		for (Organization item : organization.getOrganizations()) {
			boolean status = this.isSubordinateManagers(item, user);
			if (status) {
				return true;
			}
		}
		return false;
	}

}
