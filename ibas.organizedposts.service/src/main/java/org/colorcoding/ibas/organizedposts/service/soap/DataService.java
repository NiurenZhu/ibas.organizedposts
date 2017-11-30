package org.colorcoding.ibas.organizedposts.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.cxf.WebServicePath;
import org.colorcoding.ibas.organizedposts.repository.*;
import org.colorcoding.ibas.organizedposts.bo.ownership.*;
import org.colorcoding.ibas.organizedposts.bo.position.*;
import org.colorcoding.ibas.organizedposts.bo.post.*;

/**
* OrganizedPosts 数据服务JSON
*/
@WebService
@WebServicePath("data")
public class DataService extends BORepositoryOrganizedPosts {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-数据权限
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Ownership> fetchOwnership(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchOwnership(criteria, token);
    }

    /**
     * 保存-数据权限
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Ownership> saveOwnership(@WebParam(name = "bo") Ownership bo, @WebParam(name = "token") String token) {
        return super.saveOwnership(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-职位
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Position> fetchPosition(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchPosition(criteria, token);
    }

    /**
     * 保存-职位
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Position> savePosition(@WebParam(name = "bo") Position bo, @WebParam(name = "token") String token) {
        return super.savePosition(bo, token);
    }

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-岗位
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Post> fetchPost(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchPost(criteria, token);
    }

    /**
     * 保存-岗位
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Post> savePost(@WebParam(name = "bo") Post bo, @WebParam(name = "token") String token) {
        return super.savePost(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
