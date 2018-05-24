package org.colorcoding.ibas.organizedposts;

/**
 * 我的配置项
 */
public class MyConfiguration extends org.colorcoding.ibas.bobas.MyConfiguration {

	/**
	 * 模块标识
	 */
	public static final String MODULE_ID = "d67cc3ce-2713-4de1-96d6-de884c1d68b3";

	/**
	 * 命名空间
	 */
	public static final String NAMESPACE_ROOT = "http://colorcoding.org/ibas/organizedposts/";

	/**
	 * 数据命名空间
	 */
	public static final String NAMESPACE_DATA = NAMESPACE_ROOT + "data";

	/**
	 * 业务对象命名空间
	 */
	public static final String NAMESPACE_BO = NAMESPACE_ROOT + "bo";

	/**
	 * 服务命名空间
	 */
	public static final String NAMESPACE_SERVICE = NAMESPACE_ROOT + "service";
	/**
	 * 配置项目-文件文件夹
	 */
	public final static String CONFIG_ITEM_ORGANIZEDPOSTS_FILE_FOLDER = "OPFileFolder";
	/**
	 * 配置项目-组织缓存刷新时间
	 */
	public final static String CONFIG_ITEM_ORGANIZATION_REFRESH_TIME = "OrgRefreshTime";
	/**
	 * 配置项目-角色数据过滤，优先可用
	 */
	public final static String CONFIG_ITEM_ROLE_DATA_FIRST_AVAILABLE = "RoleDataFirstAvailable";

}
