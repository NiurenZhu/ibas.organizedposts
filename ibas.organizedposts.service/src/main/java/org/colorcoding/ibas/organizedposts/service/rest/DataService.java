package org.colorcoding.ibas.organizedposts.service.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.Criteria;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.organizedposts.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedposts.bo.position.Position;
import org.colorcoding.ibas.organizedposts.bo.post.Post;
import org.colorcoding.ibas.organizedposts.repository.BORepositoryOrganizedPosts;

/**
 * OrganizedPosts 数据服务JSON
 */
@Path("data")
public class DataService extends BORepositoryOrganizedPosts {

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-数据权限
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("fetchOwnership")
	public OperationResult<Ownership> fetchOwnership(Criteria criteria, @QueryParam("token") String token) {
		return super.fetchOwnership(criteria, token);
	}

	/**
	 * 保存-数据权限
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("saveOwnership")
	public OperationResult<Ownership> saveOwnership(Ownership bo, @QueryParam("token") String token) {
		return super.saveOwnership(bo, token);
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-职位
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("fetchPosition")
	public OperationResult<Position> fetchPosition(Criteria criteria, @QueryParam("token") String token) {
		return super.fetchPosition(criteria, token);
	}

	/**
	 * 保存-职位
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("savePosition")
	public OperationResult<Position> savePosition(Position bo, @QueryParam("token") String token) {
		return super.savePosition(bo, token);
	}

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-岗位
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("fetchPost")
	public OperationResult<Post> fetchPost(Criteria criteria, @QueryParam("token") String token) {
		return super.fetchPost(criteria, token);
	}

	/**
	 * 保存-岗位
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("savePost")
	public OperationResult<Post> savePost(Post bo, @QueryParam("token") String token) {
		return super.savePost(bo, token);
	}

	// --------------------------------------------------------------------------------------------//

}
