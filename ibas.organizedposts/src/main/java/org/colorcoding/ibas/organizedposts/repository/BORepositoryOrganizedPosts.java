package org.colorcoding.ibas.organizedposts.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.initialfantasy.repository.BORepositoryInitialFantasy;
import org.colorcoding.ibas.organizedposts.bo.ownership.IOwnership;
import org.colorcoding.ibas.organizedposts.bo.ownership.Ownership;
import org.colorcoding.ibas.organizedposts.bo.position.IPosition;
import org.colorcoding.ibas.organizedposts.bo.position.Position;
import org.colorcoding.ibas.organizedposts.bo.post.IPost;
import org.colorcoding.ibas.organizedposts.bo.post.Post;

/**
 * OrganizedPosts仓库
 */
public class BORepositoryOrganizedPosts extends BORepositoryInitialFantasy
		implements IBORepositoryOrganizedPostsSvc, IBORepositoryOrganizedPostsApp {

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
	public OperationResult<Ownership> fetchOwnership(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Ownership.class);
	}

	/**
	 * 查询-数据权限（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IOwnership> fetchOwnership(ICriteria criteria) {
		return new OperationResult<IOwnership>(this.fetchOwnership(criteria, this.getUserToken()));
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
	public OperationResult<Ownership> saveOwnership(Ownership bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-数据权限（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IOwnership> saveOwnership(IOwnership bo) {
		return new OperationResult<IOwnership>(this.saveOwnership((Ownership) bo, this.getUserToken()));
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
	public OperationResult<Position> fetchPosition(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Position.class);
	}

	/**
	 * 查询-职位（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IPosition> fetchPosition(ICriteria criteria) {
		return new OperationResult<IPosition>(this.fetchPosition(criteria, this.getUserToken()));
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
	public OperationResult<Position> savePosition(Position bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-职位（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IPosition> savePosition(IPosition bo) {
		return new OperationResult<IPosition>(this.savePosition((Position) bo, this.getUserToken()));
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
	public OperationResult<Post> fetchPost(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Post.class);
	}

	/**
	 * 查询-岗位（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IPost> fetchPost(ICriteria criteria) {
		return new OperationResult<IPost>(this.fetchPost(criteria, this.getUserToken()));
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
	public OperationResult<Post> savePost(Post bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-岗位（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IPost> savePost(IPost bo) {
		return new OperationResult<IPost>(this.savePost((Post) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

}
