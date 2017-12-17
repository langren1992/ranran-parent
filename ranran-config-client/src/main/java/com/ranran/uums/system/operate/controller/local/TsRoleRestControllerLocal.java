package com.ranran.uums.system.operate.controller.local;

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
        String reqData = this.wrapperJson(request, new ErrorCode(100, "/tsRole/selectByCondition.html json trans error"));
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
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsRole/selectByCondition.html json trans error"));
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
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectByCondition.html json trans error"));
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
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectByCondition.html json trans error"));
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
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectByCondition.html json trans error"));
        TsRoleNotUserVo tsRoleNotUserVo = JSONObject.parseObject(reqData,TsRoleNotUserVo.class);
        return this.listResult(tsRoleService.selectRoleNotUser(tsRoleNotUserVo));
    }

    /**
     * 新增，更新角色信息
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult updateRoleBatch(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectByCondition.html json trans error"));
        TsRoleBatchVo tsRoleBatchVo = JSONObject.parseObject(reqData,TsRoleBatchVo.class);
        return this.saveResult(tsRoleService.updateRoleBatch(tsRoleBatchVo));
    }
}
