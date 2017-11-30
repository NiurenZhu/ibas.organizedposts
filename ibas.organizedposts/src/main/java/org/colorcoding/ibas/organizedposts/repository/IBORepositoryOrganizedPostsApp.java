package org.colorcoding.ibas.organizedposts.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.initialfantasy.repository.IBORepositoryInitialFantasyApp;
import org.colorcoding.ibas.organizedposts.bo.ownership.*;
import org.colorcoding.ibas.organizedposts.bo.position.*;
import org.colorcoding.ibas.organizedposts.bo.post.*;

/**
* OrganizedPosts仓库应用
*/
public interface IBORepositoryOrganizedPostsApp extends IBORepositoryInitialFantasyApp {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-数据权限
     * @param criteria 查询
     * @return 操作结果
     */
    IOperationResult<IOwnership> fetchOwnership(ICriteria criteria);

    /**
     * 保存-数据权限
     * @param bo 对象实例
     * @return 操作结果
     */
    IOperationResult<IOwnership> saveOwnership(IOwnership bo);

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-职位
     * @param criteria 查询
     * @return 操作结果
     */
    IOperationResult<IPosition> fetchPosition(ICriteria criteria);

    /**
     * 保存-职位
     * @param bo 对象实例
     * @return 操作结果
     */
    IOperationResult<IPosition> savePosition(IPosition bo);

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-岗位
     * @param criteria 查询
     * @return 操作结果
     */
    IOperationResult<IPost> fetchPost(ICriteria criteria);

    /**
     * 保存-岗位
     * @param bo 对象实例
     * @return 操作结果
     */
    IOperationResult<IPost> savePost(IPost bo);

    //--------------------------------------------------------------------------------------------//

}
