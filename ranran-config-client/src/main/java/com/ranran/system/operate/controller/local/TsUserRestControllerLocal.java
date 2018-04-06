package com.ranran.system.operate.controller.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.system.operate.controller.TsUserRestController;
import com.ranran.system.operate.service.TsUserService;
import com.ranran.system.operate.vo.TsUserSearchVo;
import com.ranran.system.operate.vo.TsUserUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class TsUserRestControllerLocal extends RestBaseController implements TsUserRestController {

    @Autowired
    private TsUserService tsUserService;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult selectUser(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsUser/selectUser.html"));
        TsUserSearchVo tsUserSearchVo = JSONObject.parseObject(reqData,TsUserSearchVo.class);
        return this.listResult(tsUserService.selectUser(tsUserSearchVo));
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult updateUsers(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsUser/updateUsers.html"));
        List<TsUserUpdateVo> tsUserUpdateVos = JSONArray.parseArray(reqData,TsUserUpdateVo.class);
        return this.saveResult(tsUserService.updateUsers(tsUserUpdateVos));
    }

    /**
     * 初始化密码
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult updateUserPwd(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsUser/updateUsers.html"));
        TsUserUpdateVo tsUserUpdateVo = JSONObject.parseObject(reqData,TsUserUpdateVo.class);
        return this.saveResult(tsUserService.updateUserPwd(tsUserUpdateVo));
    }
}
