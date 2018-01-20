package com.ranran.uums.system.operate.controller;


import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 部门信息请求控制
 *
 * Created by zengrui on 2017-08-11 12:10:02.
 */
public interface TsUserRestController {

    /**
     * 查询部门信息，生成树形菜单
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult selectUser(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult updateUsers(HttpServletRequest request);


    /**
     * 初始化密码
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult updateUserPwd(HttpServletRequest request);


}
