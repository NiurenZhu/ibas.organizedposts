package org.colorcoding.ibas.organizedposts.test.bo;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.organizedposts.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;
import org.colorcoding.ibas.organizedposts.repository.IBORepositoryOrganizedPostsApp;

import junit.framework.TestCase;

/**
 * 数据权限 测试
 * 
 */
public class testOwnership extends TestCase {
	/**
	 * 获取连接口令
	 */
	String getToken() {
		return "";
	}

	/**
	 * 基本项目测试
	 * 
	 * @throws Exception
	 */
	public void testBasicItems() throws Exception {
		Ownership bo = new Ownership();
		// 测试属性赋值

		// 测试对象的保存和查询
		IOperationResult<?> operationResult = null;
		ICriteria criteria = null;
		IBORepositoryOrganizedPostsApp boRepository = new BORepositoryOrganizedPosts();
		// 设置用户口令
		boRepository.setUserToken(this.getToken());

		// 测试保存
		operationResult = boRepository.saveOwnership(bo);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
		Ownership boSaved = (Ownership) operationResult.getResultObjects().firstOrDefault();

		// 测试查询
		criteria = boSaved.getCriteria();
		criteria.setResultCount(10);
		operationResult = boRepository.fetchOwnership(criteria);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);

	}

}
