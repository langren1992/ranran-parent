package com.ranran.system.operate.controller;

import com.ranran.core.ResponseResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 资源操作控制结构
 */
public interface TsRoleRestController {

    /**
     * 角色资源条件查询
     * @param request
     * @return ResponseResult
     */
    public ResponseResult pageRoleCondition(HttpServletRequest request);

    /**
     * 查询角色资源
     * @param request
     * @return ResponseResult
     */
    public ResponseResult selectRoleResource(HttpServletRequest request);

    /**
     * 查询角色下的资源权限
     * @param request
     * @return ResponseResult
     */
    public ResponseResult selectRoleResPermi(HttpServletRequest request);

    /**
     * 查询角色下的所有用户
     * @param request
     * @return ResponseResult
     */
    public ResponseResult selectRoleUser(HttpServletRequest request);

    /**
     * 查询所有不属于该角色的用户
     * @param request
     * @return ResponseResult
     */
    public ResponseResult selectRoleNotUser(HttpServletRequest request);

    /**
     * 生成角色资源、权限关联关系
     * @param request
     * @return
     */
    public ResponseResult optRoleResRal(HttpServletRequest request);

    /**
     * 生成角色权限关联关系
     * @param request 请求参数
     * @return ResponseResult
     */
    public ResponseResult optRoleResPermiRal(HttpServletRequest request);

    /**
     * 生成角色用户关联关系
     * @param request
     * @return
     */
    public ResponseResult optRoleUserRal(HttpServletRequest request);

    /**
     * 新增、更新、启用、停用角色信息
     * @param request
     * @return
     */
    public ResponseResult updateRoleBatch(HttpServletRequest request);

    /**
     * 删除角色
     * @param request
     * @return
     */
    public ResponseResult deleteRole(HttpServletRequest request);

}
