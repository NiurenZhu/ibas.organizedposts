package org.colorcoding.ibas.organizedposts.data;

import org.colorcoding.ibas.bobas.mapping.Value;

/**
 * 所有权 项
 *
 * @author Niuren.Zhu
 */
public enum emOwnershipSign {
	/**
	 * 审批
	 */
	@Value("A")
	APPROVAL,

	/**
	 * 同级
	 */
	@Value("E")
	EQUALLEVEL,

	/**
	 * 上级
	 */
	@Value("H")
	HIGHERLEVEL,

	/**
	 * 下级
	 */
	@Value("L")
	LOWERLEVEL,

	/**
	 * 其他
	 */
	@Value("O")
	OTHERS,

	/**
	 * 项目
	 */
	@Value("P")
	PROJECT,

	/**
	 * 自身
	 */
	@Value("S")
	SELF,

	/**
	 * 团队
	 */
	@Value("T")
	TEAMS,
}
