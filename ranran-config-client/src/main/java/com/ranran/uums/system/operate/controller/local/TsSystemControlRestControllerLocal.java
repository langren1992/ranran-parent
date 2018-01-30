package com.ranran.uums.system.operate.controller.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsSystemControlRestController;
import com.ranran.uums.system.operate.controller.impl.TsSystemControlRestControllerImpl;
import com.ranran.uums.system.operate.service.TsSystemControlService;
import com.ranran.uums.system.operate.vo.TsSystemControlSelectVo;
import com.ranran.uums.system.operate.vo.TsSystemControlUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 系统控制参数请求处理
 * @creator zengrui 2018-01-30 10:13
 */
@Component
public class TsSystemControlRestControllerLocal extends RestBaseController implements TsSystemControlRestController {

    @Autowired
    private TsSystemControlService tsSystemControlService;

    /**
    * 查询部门信息，生成树形菜单
    *
    * @param request 请求参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult selectTsSystemControl(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/TsSystemControl/selectTsSystemControl.html"));
        TsSystemControlSelectVo tsSystemControlSelectVo = JSONObject.parseObject(reqData,TsSystemControlSelectVo.class);
        return this.listResult(tsSystemControlService.selectTsSystemControl(tsSystemControlSelectVo));
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）部门
    *
    * @param request 参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult updateTsSystemControls(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/TsSystemControl/updateTsSystemControl.html"));
        List<TsSystemControlUpdateVo> tsSystemControlUpdateVos = JSONArray.parseArray(reqData,TsSystemControlUpdateVo.class);
        return this.saveResult(tsSystemControlService.updateTsSystemControls(tsSystemControlUpdateVos));
    }

}
