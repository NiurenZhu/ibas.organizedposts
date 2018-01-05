package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;

public class OthersMatchingStrategy extends BasisMatchingStrategy {
	/**
	 * 优先级
	 *
	 * @return
	 */
	@Override
	public Integer getPriority() {
		return -1;
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
		return true;
	}
}
