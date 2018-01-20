package com.ranran.uums.system.operate.controller.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsDeptRestController;
import com.ranran.uums.system.operate.controller.impl.TsDeptRestControllerImpl;
import com.ranran.uums.system.operate.service.TsDeptService;
import com.ranran.uums.system.operate.vo.TsDeptSearchVo;
import com.ranran.uums.system.operate.vo.TsDeptUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class TsDeptRestControllerLocal extends RestBaseController implements TsDeptRestController {

    @Autowired
    private TsDeptService tsDeptService;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult selectDept(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsRole/selectRoleResPermi.html"));
        TsDeptSearchVo tsDeptSearchVo = JSONObject.parseObject(reqData,TsDeptSearchVo.class);
        return this.listResult(tsDeptService.selectDept(tsDeptSearchVo));
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult updateDepts(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsRole/selectRoleResPermi.html"));
        List<TsDeptUpdateVo> tsDeptUpdateVos = JSONArray.parseArray(reqData,TsDeptUpdateVo.class);
        return this.saveResult(tsDeptService.updateDepts(tsDeptUpdateVos));
    }
}
