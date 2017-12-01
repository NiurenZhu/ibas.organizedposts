package org.colorcoding.ibas.bobas.ownership.post.matching;

import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.bobas.ownership.IDataOwnership;

public interface IMatchingStrategy {

	/**
	 * 设置参数
	 *
	 * @param value 参数
	 */
	void setParameter(String value);

	/**
	 * 优先级
	 *
	 * @return
	 */
	Integer getPriority();

	/**
	 * 判断当前BO是否适用此规则
	 *
	 * @param bo
	 * @param user
	 * @return
	 */
	boolean match(IDataOwnership bo, IUser user);
}
