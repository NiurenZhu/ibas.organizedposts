package org.colorcoding.ibas.organizedposts.test.bo;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.organizedposts.bo.position.Position;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;
import org.colorcoding.ibas.organizedposts.repository.IBORepositoryOrganizedPostsApp;

import junit.framework.TestCase;

/**
 * 职位 测试
 * 
 */
public class testPosition extends TestCase {
	/**
	 * 获取连接口令
	 */
	String getToken() {
		return "0ac64e63ff2deb1ee8afc4435eb7a2d9";
	}

	/**
	 * 基本项目测试
	 * 
	 * @throws Exception
	 */
	public void testBasicItems() throws Exception {
		Position bo = new Position();
		// 测试属性赋值

		// 测试对象的保存和查询
		IOperationResult<?> operationResult = null;
		ICriteria criteria = null;
		IBORepositoryOrganizedPostsApp boRepository = new BORepositoryOrganizedPosts();
		// 设置用户口令
		boRepository.setUserToken(this.getToken());

		// 测试保存
		operationResult = boRepository.savePosition(bo);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
		Position boSaved = (Position) operationResult.getResultObjects().firstOrDefault();

		// 测试查询
		criteria = boSaved.getCriteria();
		criteria.setResultCount(10);
		operationResult = boRepository.fetchPosition(criteria);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);

	}

}
