package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;

public class SelfMatchingStrategy extends BasisMatchingStrategy {
	/**
	 * 判断当前BO是否适用此规则
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	@Override
	public boolean match(IDataOwnership bo, IUser user) {
		if(bo.getDataOwner().equals(user.getId())){
			return true;
		}
		return false;
	}
}
