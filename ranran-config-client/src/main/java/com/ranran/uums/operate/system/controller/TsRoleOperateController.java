package com.ranran.uums.operate.system.controller;

import com.ranran.core.ResponseResult;
import com.ranran.uums.operate.system.model.TsRoleUpdateEnable;
import com.ranran.uums.operate.system.service.TsRoleUpdateEnableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("operate/system/tsRole")
public class TsRoleOperateController {

    //日志
    private static Logger LOGGER = LoggerFactory.getLogger(TsRoleOperateController.class);

    @Autowired
    private TsRoleUpdateEnableService tsRoleUpdateEnableService;

    @PostMapping("updateEnable")
    public ResponseResult updateEnable(TsRoleUpdateEnable tsRole){
        ResponseResult responseResult = new ResponseResult();
        try {
            Integer updateResult = this.tsRoleUpdateEnableService.updateEnable(tsRole);
            if (updateResult <= 0){
                responseResult.setSuccess(false);
                responseResult.setMessage(ResponseResult.UPDATE_ENABLE_FAIL);
            } else {
                responseResult.setSuccess(true);
                responseResult.setMessage(ResponseResult.UPDATE_ENABLE_SUCCESS);
            }
        } catch (Exception e){
            LOGGER.error("operate/system/tsRole/updateEnable fail cause by : {}",e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }
}