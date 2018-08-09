package org.colorcoding.ibas.bobas.organization.test.post;

import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.data.DateTime;
import org.colorcoding.ibas.bobas.organization.OrganizationFactory;
import org.colorcoding.ibas.bobas.organization.post.OrganizationManager;
import org.colorcoding.ibas.bobas.organization.post.OrganizationalRelationship;
import org.colorcoding.ibas.bobas.repository.InvalidTokenException;
import org.colorcoding.ibas.initialfantasy.bo.organization.IUser;
import org.colorcoding.ibas.initialfantasy.bo.organization.User;
import org.colorcoding.ibas.organizedposts.bo.position.IPosition;
import org.colorcoding.ibas.organizedposts.bo.position.Position;
import org.colorcoding.ibas.organizedposts.bo.post.IPost;
import org.colorcoding.ibas.organizedposts.bo.post.Post;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;

import junit.framework.TestCase;

public class TestOrganization extends TestCase {

	protected BORepositoryOrganizedPosts boRepository;
	private int tmpCode;
	protected IUser[] users;
	protected IPosition[] positions;

	public void testOrganizationManager() throws InvalidTokenException {
		try {
			this.initialize();
			// 开始测试
			// 多次测试后可清空数据
			// DELETE FROM "CC_OP_POST" WHERE "Code" like 'PT%';
			// DELETE FROM "CC_OP_PSTN" WHERE "Code" like 'PS%';
			// DELETE FROM "CC_SYS_USER" WHERE "Code" like 'UR%';
			OrganizationManager orgManager = (OrganizationManager) OrganizationFactory.create().createManager();
			orgManager.initialize(true);// 已缓存，重新加载组织
			String[] roles = orgManager
					.getRoles(org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[10]));
			assertEquals("他只有一个角色", roles.length, 1);
			assertEquals("他的角色是开发工程师", roles[0], String.format("PS%06d", tmpCode + 7));
			OrganizationalRelationship relationship = orgManager.getRelationship(
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[0]), // 老板
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[1])); // 财务部经理
			assertEquals("用户1是用户0的下级", relationship, OrganizationalRelationship.SUBORDINATE);
			relationship = orgManager.getRelationship(
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[9]), // 阿米巴01负责人
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[13])); // 阿米巴02负责人
			assertEquals("用户13是用户9的同级", relationship, OrganizationalRelationship.COLLEAGUES);
			relationship = orgManager.getRelationship(
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[17]), // 阿米巴03负责人
					org.colorcoding.ibas.initialfantasy.bo.shell.User.create(this.users[8])); // 思博总经理
			assertEquals("用户8是用户17的上级", relationship, OrganizationalRelationship.LEADER);
		} catch (Exception error) {
			assertEquals(error.getMessage(), 0, 1);
		}
	}

	/**
	 * 初始化
	 */
	protected void initialize() throws Exception {
		this.boRepository = new BORepositoryOrganizedPosts();
		this.boRepository.setUserToken(OrganizationFactory.SYSTEM_USER.getToken());
		String tmp = String.valueOf(DateTime.getNow().getTime());
		this.tmpCode = Integer.valueOf(tmp.substring(tmp.length() - 6));
		this.initializeUsers();
		this.initializePositions();
		this.initializePosts();
	}

	/**
	 * 初始化用户
	 */
	protected void initializeUsers() throws Exception {
		this.users = new IUser[20];
		IOperationResult<IUser> operationResult = null;
		for (int i = 0; i < 20; i++) {
			IUser user = new User();
			user.setCode(String.format("UR%06d", tmpCode + i));
			user.setName(user.getCode() + "-" + Faker.ChineseName());
			operationResult = boRepository.saveUser(user);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			this.users[i] = operationResult.getResultObjects().firstOrDefault();
		}

	}

	/**
	 * 初始化职位
	 */
	protected void initializePositions() throws Exception {
		this.positions = new IPosition[9];
		String[] positionNames = { "CEO", "经理", "会计", "人事专员", "行政助理", "思博总经理", "团队负责人", "开发工程师", "实施顾问" };
		IOperationResult<IPosition> operationResult = null;
		for (int i = 0; i < 9; i++) {
			IPosition position = new Position();
			position.setCode(String.format("PS%06d", tmpCode + i));
			position.setName(position.getCode() + "-" + positionNames[i]);
			operationResult = boRepository.savePosition(position);
			assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
			this.positions[i] = operationResult.getResultObjects().firstOrDefault();
		}
	}

	/**
	 * 初始化岗位
	 */
	protected void initializePosts() throws Exception {
		IOperationResult<IPost> operationResult = null;
		// #region 公司
		IPost post = new Post();
		post.setBelonging(-1);
		post.setCode(String.format("PT%06d", tmpCode + 0));
		post.setName("老板");
		post.setPosition(this.positions[0].getCode()); // CEO
		post.setUserCode(this.users[0].getCode());
		// #endregion
		// #region ****财务部
		IPost post1 = post.getPosts().create();
		post1.setCode(String.format("PT%06d", tmpCode + 1));
		post1.setName("财务经理");
		post1.setPosition(this.positions[1].getCode()); // 经理
		post1.setUserCode(this.users[1].getCode());

		IPost post2 = post1.getPosts().create();
		post2.setCode(String.format("PT%06d", tmpCode + 2));
		post2.setName("会计-工资核算");
		post2.setPosition(this.positions[2].getCode()); // 会计
		post2.setUserCode(this.users[2].getCode());

		IPost post3 = post1.getPosts().create();
		post3.setCode(String.format("PT%06d", tmpCode + 3));
		post3.setName("会计-费用报销");
		post3.setPosition(this.positions[2].getCode()); // 会计
		post3.setUserCode(this.users[3].getCode());
		// #endregion
		// #region ****人事部
		IPost post4 = post.getPosts().create();
		post4.setCode(String.format("PT%06d", tmpCode + 4));
		post4.setName("人事经理");
		post4.setPosition(this.positions[1].getCode()); // 经理
		post4.setUserCode(this.users[4].getCode());

		IPost post5 = post4.getPosts().create();
		post5.setCode(String.format("PT%06d", tmpCode + 5));
		post5.setName("人事-招聘");
		post5.setPosition(this.positions[3].getCode()); // 人事专员
		post5.setUserCode(this.users[5].getCode());

		IPost post6 = post4.getPosts().create();
		post6.setCode(String.format("PT%06d", tmpCode + 6));
		post6.setName("人事-前台");
		post6.setPosition(this.positions[3].getCode()); // 人事专员
		post6.setUserCode(this.users[6].getCode());

		IPost post7 = post4.getPosts().create();
		post7.setCode(String.format("PT%06d", tmpCode + 7));
		post7.setName("行政助理");
		post7.setPosition(this.positions[4].getCode()); // 行政助理
		post7.setUserCode(this.users[7].getCode());
		// #endregion
		// #region ****思博分公司
		IPost post8 = post.getPosts().create();
		post8.setCode(String.format("PT%06d", tmpCode + 8));
		post8.setName("思博总经理");
		post8.setPosition(this.positions[5].getCode()); // 思博总经理
		post8.setUserCode(this.users[8].getCode());
		// #endregion
		// #region ********阿米巴团队01
		IPost post9 = post8.getPosts().create();
		post9.setCode(String.format("PT%06d", tmpCode + 9));
		post9.setName("阿米巴01负责人");
		post9.setPosition(this.positions[6].getCode()); // 团队负责人
		post9.setUserCode(this.users[9].getCode());

		IPost post10 = post9.getPosts().create();
		post10.setCode(String.format("PT%06d", tmpCode + 10));
		post10.setName("开发工程师-前端");
		post10.setPosition(this.positions[7].getCode()); // 开发工程师
		post10.setUserCode(this.users[10].getCode());

		IPost post11 = post9.getPosts().create();
		post11.setCode(String.format("PT%06d", tmpCode + 11));
		post11.setName("开发工程师-.Net");
		post11.setPosition(this.positions[7].getCode()); // 开发工程师
		post11.setUserCode(this.users[11].getCode());

		IPost post12 = post9.getPosts().create();
		post12.setCode(String.format("PT%06d", tmpCode + 12));
		post12.setName("开发工程师-.Net");
		post12.setPosition(this.positions[7].getCode()); // 开发工程师
		post12.setUserCode(this.users[12].getCode());
		// #endregion
		// #region ********阿米巴团队02
		IPost post13 = post8.getPosts().create();
		post13.setCode(String.format("PT%06d", tmpCode + 13));
		post13.setName("阿米巴02负责人");
		post13.setPosition(this.positions[6].getCode()); // 团队负责人
		post13.setUserCode(this.users[13].getCode());

		IPost post14 = post13.getPosts().create();
		post14.setCode(String.format("PT%06d", tmpCode + 14));
		post14.setName("开发工程师-前端");
		post14.setPosition(this.positions[7].getCode()); // 开发工程师
		post14.setUserCode(this.users[10].getCode()); // 两个团队共用一个开发工程师-前端

		IPost post15 = post13.getPosts().create();
		post15.setCode(String.format("PT%06d", tmpCode + 15));
		post15.setName("开发工程师-Java");
		post15.setPosition(this.positions[7].getCode()); // 开发工程师
		post15.setUserCode(this.users[15].getCode());

		IPost post16 = post13.getPosts().create();
		post16.setCode(String.format("PT%06d", tmpCode + 16));
		post16.setName("开发工程师-Java");
		post16.setPosition(this.positions[7].getCode()); // 开发工程师
		post16.setUserCode(this.users[16].getCode());
		// #endregion
		// #region ********阿米巴团队03
		IPost post17 = post8.getPosts().create();
		post17.setCode(String.format("PT%06d", tmpCode + 17));
		post17.setName("阿米巴03负责人");
		post17.setPosition(this.positions[6].getCode()); // 团队负责人
		post17.setUserCode(this.users[17].getCode());

		IPost post18 = post17.getPosts().create();
		post18.setCode(String.format("PT%06d", tmpCode + 18));
		post18.setName("实施顾问");
		post18.setPosition(this.positions[8].getCode()); // 实施顾问
		post18.setUserCode(this.users[18].getCode());

		IPost post19 = post17.getPosts().create();
		post19.setCode(String.format("PT%06d", tmpCode + 19));
		post19.setName("实施顾问");
		post19.setPosition(this.positions[8].getCode()); // 实施顾问
		post19.setUserCode(this.users[19].getCode());
		// #endregion
		post.setValidDate(DateTime.getToday());
		post.setInvalidDate(DateTime.MAX_VALUE);
		operationResult = boRepository.savePost(post);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
	}
}