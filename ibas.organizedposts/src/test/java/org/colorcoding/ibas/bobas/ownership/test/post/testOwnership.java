package org.colorcoding.ibas.bobas.ownership.test.post;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.data.DateTime;
import org.colorcoding.ibas.bobas.data.emAuthoriseType;
import org.colorcoding.ibas.bobas.data.emConditionOperation;
import org.colorcoding.ibas.bobas.data.emYesNo;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.post.OrganizationManager;
import org.colorcoding.ibas.bobas.organization.test.post.testOrganization;
import org.colorcoding.ibas.bobas.repository.InvalidTokenException;
import org.colorcoding.ibas.initialfantasy.bo.bofiltering.BOFiltering;
import org.colorcoding.ibas.initialfantasy.bo.bofiltering.IBOFiltering;
import org.colorcoding.ibas.initialfantasy.bo.bofiltering.IBOFilteringCondition;
import org.colorcoding.ibas.initialfantasy.data.emAssignedType;
import org.colorcoding.ibas.initialfantasy.data.emFilteringType;
import org.colorcoding.ibas.organizedposts.MyConfiguration;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnership;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnershipItem;
import org.colorcoding.ibas.organizedposts.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedposts.data.emOwnershipSign;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;

import java.util.UUID;

public class testOwnership extends testOrganization {
	public void testOwnership() throws InvalidTokenException {
		try {
			IOperationResult<?> operationResult = null;
			ICriteria criteria = null;
			super.initialize();
			OrganizationManager orgManager = (OrganizationManager) OrganizationFactory.create().createManager();
			orgManager.initialize(true);// 已缓存，重新加载组织
			//#region 定义数据权限
			// 定义所有权设置，阿米巴01负责人，自，all；同事，no；下属，no；上级，view
			IOwnership ownership = new Ownership();
			ownership.setActivated(emYesNo.YES);
			ownership.setBOCode(MyConfiguration.applyVariables(TestData.BUSINESS_OBJECT_CODE));
			ownership.setUserCode(users[9].getCode()); // 阿米巴01负责人
			ownership.setDefaultPermission(emAuthoriseType.NONE); // 默认无权限
			IOwnershipItem item = ownership.getOwnershipItems().create();
			item.setItemSign(emOwnershipSign.SELF);
			item.setPermission(emAuthoriseType.ALL);

			item = ownership.getOwnershipItems().create();
			item.setItemSign(emOwnershipSign.EQUALLEVEL);
			item.setPermission(emAuthoriseType.NONE);

			item = ownership.getOwnershipItems().create();
			item.setItemSign(emOwnershipSign.LOWERLEVEL);
			item.setPermission(emAuthoriseType.NONE);

			item = ownership.getOwnershipItems().create();
			item.setItemSign(emOwnershipSign.HIGHERLEVEL);
			item.setPermission(emAuthoriseType.READ);
			operationResult = boRepository.saveOwnership(ownership);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			//#endregion
			//#region 获取组织用户，以获取口令
			org.colorcoding.ibas.bobas.organization.IUser user01 = orgManager.getUser(users[9].getDocEntry()); // 阿米巴01负责人
			org.colorcoding.ibas.bobas.organization.IUser user02 = orgManager.getUser(users[13].getDocEntry()); // 阿米巴02负责人
			org.colorcoding.ibas.bobas.organization.IUser user03 = orgManager.getUser(users[8].getDocEntry()); // 思博总经理
			org.colorcoding.ibas.bobas.organization.IUser user04 = orgManager.getUser(users[11].getDocEntry()); // 开发工程师-.Net
			org.colorcoding.ibas.bobas.organization.IUser user05 = orgManager.getUser(users[3].getDocEntry()); // 会计-费用报销
			String[] roleCodes = orgManager.getRoles(user01); // 获取阿米巴01负责人-用户角色
			String roleCode = "";
			if (roleCodes.length == 1) {
				roleCode = roleCodes[0];
			}
			//#endregion
			//#region  创建阿米巴01负责人的数据
			TestData data = new TestData();
			data.setApplicationId(UUID.randomUUID().toString());
			data.setName(String.format("test_%s", DateTime.getNow().getTime()));
			data.setAssignedType(emAssignedType.USER);
			data.setAssigned("tester");
			// data.setDataOwner(User03.getObjectKey());
			BORepositoryTest boRepositoryTest = new BORepositoryTest();
			boRepositoryTest.setUserToken(user01.getToken());// 设置当前用户
			operationResult = boRepositoryTest.saveTestData(data);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			//#endregion
			// 其他人查询数据
			//#region 下级开发工程师-.Net 数据权限
			boRepositoryTest = new BORepositoryTest();
			boRepositoryTest.setUserToken(user04.getToken());// 设置当前用户，下级开发工程师-.Net
			criteria = data.getCriteria();
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("下级开发工程师-.Net检索到阿米巴01负责人的数据。", operationResult.getResultObjects().size(), 0);
			//#endregion
			//#region 同级阿米巴02负责人 数据权限
			boRepositoryTest = new BORepositoryTest();
			boRepositoryTest.setUserToken(user02.getToken());// 设置当前用户，同级阿米巴02负责人
			criteria = data.getCriteria();
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("同事阿米巴02负责人检索到阿米巴01负责人的数据。", operationResult.getResultObjects().size(), 0);
			//#endregion
			//#region 上级思博总经理 数据权限
			boRepositoryTest = new BORepositoryTest();
			boRepositoryTest.setUserToken(user03.getToken());// 设置当前用户，上级思博总经理
			criteria = data.getCriteria();
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("思博总经理没有检索到阿米巴01负责人的数据。", operationResult.getResultObjects().size(), 1);
			data = (TestData) operationResult.getResultObjects().firstOrDefault();
			data.setName("思博总经理修改了数据");
			operationResult = boRepositoryTest.saveTestData(data);
			assertNotNull("思博总经理成功修改了阿米巴01负责人的数据。", operationResult.getError());
			//#endregion
			//#region 会计-费用报销 数据权限(测试审批,未完成。。。)
			boRepositoryTest = new BORepositoryTest();
			boRepositoryTest.setUserToken(user05.getToken());// 设置当前用户，会计-费用报销
			criteria = data.getCriteria();
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("会计-费用报销检索到阿米巴01负责人的数据。", operationResult.getResultObjects().size(), 0);

			//#endregion
			// 启用过滤数据，更新过的数据
			//#region 启用过滤数据，更新过的数据
			IBOFiltering boFiltering = new BOFiltering();
			boFiltering.setBOCode(org.colorcoding.ibas.initialfantasy.MyConfiguration.applyVariables(TestData.BUSINESS_OBJECT_CODE));
			boFiltering.setRoleCode(roleCode);
			boFiltering.setFilteringType(emFilteringType.AVAILABLE);
			IBOFilteringCondition condition = boFiltering.getBOFilteringConditions().create();
			condition.setPropertyName(BOFiltering.PROPERTY_LOGINST.getName());// 更新次数
			condition.setOperation(emConditionOperation.GRATER_THAN);
			condition.setConditionValue("1");

			boRepositoryTest.setUserToken(user01.getToken());// 设置当前用户，阿米巴01负责人
			operationResult = boRepositoryTest.saveBOFiltering(boFiltering);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);

			criteria = data.getCriteria();
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("加了过滤条件的 阿米巴01负责人数据被检索到了。", operationResult.getResultObjects().size(), 0);
			data.setName("updated.");
			operationResult = boRepositoryTest.saveTestData(data);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			operationResult = boRepositoryTest.fetchTestData(criteria);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			assertEquals("没有检索到加了过滤条件的 数据。", operationResult.getResultObjects().size(), 1);
			//#endregion
		} catch (Exception error) {
			assertEquals(error.getMessage(), 0, 1);
		}
	}
}

class BORepositoryTest extends BORepositoryOrganizedPosts {
	public OperationResult<TestData> fetchTestData(ICriteria criteria) {
		return super.fetch(criteria, this.getUserToken(), TestData.class);
	}

	public OperationResult<TestData> saveTestData(TestData bo) {
		return super.save(bo, this.getUserToken());
	}

}