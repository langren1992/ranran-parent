package com.ranran.uums.system.operate.controller.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.exception.ControllerException;
import com.ranran.uums.system.model.TsRole;
import com.ranran.uums.system.operate.controller.TsRoleRestController;
import com.ranran.uums.system.operate.service.TsResourceService;
import com.ranran.uums.system.operate.service.TsRoleService;
import com.ranran.uums.system.operate.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class TsRoleRestControllerLocal extends RestBaseController implements TsRoleRestController {

    @Autowired
    private TsRoleService tsRoleService;

    /**
     * 通过查询条件查询角色
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult pageRoleCondition(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(100, "/tsRole/pageRoleCondition.html"));
        TsRoleConditionVo tsRoleConditionVo = JSONObject.parseObject(reqData, TsRoleConditionVo.class);
        ResponseResult resultBody = new ResponseResult();
        PageInfo pageInfo = tsRoleService.pageRoleCondition(tsRoleConditionVo);
        if (pageInfo.getList().size() >0){
            resultBody.rows = (pageInfo.getList().toArray());
            resultBody.total = pageInfo.getTotal();
        }else {
            resultBody.rows = new Object[]{};
            resultBody.total = pageInfo.getTotal();
        }
        return resultBody;
    }

    /**
     * 查询角色资源
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult selectRoleResource(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsRole/selectRoleResource.html"));
        TsRoleResourceVo tsRoleResourceVo = JSONObject.parseObject(reqData, TsRoleResourceVo.class);
        return this.listResult(tsRoleService.selectRoleResource(tsRoleResourceVo));
    }

    /**
     * 查询角色资源权限项
     * @param request
     * @return
     */
    @Override
    public ResponseResult selectRoleResPermi(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectRoleResPermi.html"));
        TsRoleResPermiVo tsRoleResPermiVo = JSONObject.parseObject(reqData,TsRoleResPermiVo.class);
        return this.listResult(tsRoleService.selectRoleResPermi(tsRoleResPermiVo));
    }

    /**
     * 查询角色下的所有用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    public ResponseResult selectRoleUser(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(103,"/tsRole/selectRoleUser.html"));
        TsRoleUserVo tsRoleUserVo = JSONObject.parseObject(reqData,TsRoleUserVo.class);
        return this.listResult(tsRoleService.selectRoleUser(tsRoleUserVo));
    }

    /**
     * 查询所有不属于该角色的用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    public ResponseResult selectRoleNotUser(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(104,"/tsRole/selectRoleNotUser.html"));
        TsRoleNotUserVo tsRoleNotUserVo = JSONObject.parseObject(reqData,TsRoleNotUserVo.class);
        return this.listResult(tsRoleService.selectRoleNotUser(tsRoleNotUserVo));
    }

    /**
     * 新增、更新、启用、停用角色信息
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult updateRoleBatch(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(105,"/tsRole/updateRoleBatch.html"));
        List<TsRoleUpdateVo> tsRoleUpdateVos = JSONArray.parseArray(reqData,TsRoleUpdateVo.class);
        return this.saveResult(tsRoleService.updateRoleBatch(tsRoleUpdateVos));
    }

    /**
     * 删除角色
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult deleteRole(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(106,"/tsRole/deleteRole.html"));
        TsRoleDeleteVo tsRoleDeleteVo = JSONObject.parseObject(reqData,TsRoleDeleteVo.class);
        return this.deleteResult(tsRoleService.deleteRole(tsRoleDeleteVo));
    }

    /**
     * 生成角色资源、权限关联关系
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult optRoleResRal(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(106,"/tsRole/optRoleResRal.html"));
        TsRoleResRalVo tsRoleResRalVo = JSONObject.parseObject(reqData,TsRoleResRalVo.class);
        return this.saveResult(tsRoleService.optRoleResRal(tsRoleResRalVo));
    }

    /**
     * 生成角色用户关联关系
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult optRoleUserRal(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(108,"/tsRole/optRoleUserRal.html"));
        TsRoleUserRalVo tsRoleUserRalVo = JSONObject.parseObject(reqData,TsRoleUserRalVo.class);
        return this.saveResult(tsRoleService.optRoleUserRal(tsRoleUserRalVo));
    }
}
