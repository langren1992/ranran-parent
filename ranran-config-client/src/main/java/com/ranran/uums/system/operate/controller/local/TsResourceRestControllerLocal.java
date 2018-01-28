package com.ranran.uums.system.operate.controller.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsResourceRestController;
import com.ranran.uums.system.operate.service.TsResourceService;
import com.ranran.uums.system.operate.vo.TsResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TsResourceRestControllerLocal extends RestBaseController implements TsResourceRestController {

    @Autowired
    private TsResourceService tsResourceService;

    public ResponseResult updateResData(HttpServletRequest request){
        String reqData = this.wrapperJson(request, new ErrorCode(1,"/res/updateRes.html json trans error"));
        TsResourceVo tsResourceVo = JSONObject.parseObject(reqData, TsResourceVo.class);
        return this.saveResult(tsResourceService.updateResData(tsResourceVo));
    }

}
