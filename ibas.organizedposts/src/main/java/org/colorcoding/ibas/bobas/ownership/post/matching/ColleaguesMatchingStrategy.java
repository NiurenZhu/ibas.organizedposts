package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.message.Logger;
import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.post.OrganizationManager;
import org.colorcoding.ibas.bobas.organization.post.OrganizationalRelationship;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;

/**
 * 匹配同级同事
 */
public class ColleaguesMatchingStrategy extends BasisMatchingStrategy {
	/**
	 * 判断当前BO是否适用此规则
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	@Override
	public boolean match(IDataOwnership bo, IUser user) {
		// 操作用户是否有权限读取当前数据
		IUser owner = this.getOrganizationManager().getUser(bo.getDataOwner());
		// 数据所有者配置了权限
		OrganizationalRelationship relationship = this.getOrganizationManager()
				.getRelationship(owner, user);
		Logger.log(MSG_OWNERSHIP_JUDGER_RELATIONSHIP, user, relationship, owner);
		return relationship == OrganizationalRelationship.SUBORDINATE;

	}

	private OrganizationManager getOrganizationManager() {
		return (OrganizationManager) OrganizationFactory.create().createManager();
	}
}
