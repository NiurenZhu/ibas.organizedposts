package org.colorcoding.ibas.organizedposts.test.bo;

import junit.framework.TestCase;
import org.colorcoding.ibas.bobas.data.*;
import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.organizedposts.data.*;
import org.colorcoding.ibas.organizedposts.bo.post.*;
import org.colorcoding.ibas.organizedposts.repository.*;

/**
* 岗位 测试
* 
*/
public class testPost extends TestCase {
    /**
     * 获取连接口令
    */
    String getToken() {
        return "0ac64e63ff2deb1ee8afc4435eb7a2d9";
    }
    
    /**
     * 基本项目测试
     * @throws Exception 
    */
    public void testBasicItems() throws Exception {
        Post bo = new Post();
        // 测试属性赋值


        // 测试岗位
        IPost post = bo.getPosts().create();
        // 测试属性赋值
        

        // 测试对象的保存和查询
        IOperationResult<?> operationResult = null;
        ICriteria criteria = null;
        IBORepositoryOrganizedPostsApp boRepository = new BORepositoryOrganizedPosts();
        //设置用户口令
        boRepository.setUserToken(this.getToken());

        // 测试保存
        operationResult = boRepository.savePost(bo);
        assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
        Post boSaved = (Post)operationResult.getResultObjects().firstOrDefault();


        // 测试查询
        criteria = boSaved.getCriteria();
        criteria.setResultCount(10);
        operationResult = boRepository.fetchPost(criteria);
        assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);


    }

}
