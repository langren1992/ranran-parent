package com.ranran.uums.system.operate.controller.impl;


import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.uums.system.operate.controller.TsRoleRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/11.
 */
@RestController(value = "TsRoleRestController")
@RequestMapping("/tsRole")
public class TsRoleRestControllerImpl extends RestBaseController implements TsRoleRestController{

    @Autowired
    private TsRoleRestController tsRoleRestController;

    /**
     * 根据条件查询功能和排序功能
     *
     * @param request
     * @return PageInfo
     */
    @PostMapping("/selectByCondition.html")
    public ResponseResult pageRoleCondition(HttpServletRequest request){
        return tsRoleRestController.pageRoleCondition(request);
    }

    /**
     * 查询角色资源
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/selectRoleResource.html")
    public ResponseResult selectRoleResource(HttpServletRequest request) {
        return tsRoleRestController.selectRoleResource(request);
    }

    /**
     * 查询角色信息下的资源权限
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/selectRoleResPermi.html")
    public ResponseResult selectRoleResPermi(HttpServletRequest request) {
        return tsRoleRestController.selectRoleResPermi(request);
    }

    /**
     * 查询角色下的所有用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    @PostMapping("/selectRoleUser.html")
    public ResponseResult selectRoleUser(HttpServletRequest request) {
        return tsRoleRestController.selectRoleUser(request);
    }

    /**
     * 查询所有不属于该角色的用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    @PostMapping("/selectRoleNotUser.html")
    public ResponseResult selectRoleNotUser(HttpServletRequest request) {
        return tsRoleRestController.selectRoleNotUser(request);
    }

    /**
     * 新增，更新角色信息
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/updateRoles.html")
    public ResponseResult updateRoleBatch(HttpServletRequest request) {
        return tsRoleRestController.updateRoleBatch(request);
    }

    /**
     * 删除角色
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/deleteRole.html")
    public ResponseResult deleteRole(HttpServletRequest request) {
        return tsRoleRestController.deleteRole(request);
    }

    /**
     * 生成角色资源、权限关联关系
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/optRoleResRal.html")
    public ResponseResult optRoleResRal(HttpServletRequest request) {
        return tsRoleRestController.optRoleResRal(request);
    }

    /**
     * 生成角色权限关联关系
     *
     * @param request 请求参数
     * @return ResponseResult
     */
    @Override
    @PostMapping("/optRoleResPermiRal.html")
    public ResponseResult optRoleResPermiRal(HttpServletRequest request) {
        return tsRoleRestController.optRoleResPermiRal(request);
    }

    /**
     * 生成角色用户关联关系
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/optRoleUserRal.html")
    public ResponseResult optRoleUserRal(HttpServletRequest request) {
        return tsRoleRestController.optRoleUserRal(request);
    }


}
