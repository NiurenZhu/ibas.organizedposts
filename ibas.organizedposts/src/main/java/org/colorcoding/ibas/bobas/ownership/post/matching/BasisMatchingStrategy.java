package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;

public abstract class BasisMatchingStrategy implements IMatchingStrategy {
	protected static final String MSG_ACCESS_AUTHORITY_JUDGER_JUDGING = "judger: [%s] is [%s] of [%s].";
	protected static final String MSG_ACCESS_AUTHORITY_JUDGER_JUDGMENT_COMPLETION = "judger: [%s] is [%s] of [%s].";
	protected static final String MSG_OWNERSHIP_JUDGER_RELATIONSHIP = "judger: [%s] is [%s] of [%s].";
	/**
	 * 设置参数
	 *
	 * @param value 参数
	 */
	@Override
	public void setParameter(String value) {

	}

	/**
	 * 优先级
	 *
	 * @return
	 */
	@Override
	public Integer getPriority() {
		return 0;
	}

	/**
	 * 判断当前BO是否适用此规则
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	@Override
	public boolean match(IDataOwnership bo, IUser user) {
		return false;
	}
}
