package com.ranran.system.query.controller;

import com.ranran.core.ResponseResult;
import com.ranran.system.query.service.TsRoleSearchService;
import com.ranran.system.query.vo.TsRoleSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("system/query/tsRole")
public class TsRoleQueryController {

    //日志
    private static Logger LOGGER = LoggerFactory.getLogger(TsRoleQueryController.class);

    @Autowired
    private TsRoleSearchService tsRoleSearchService;

    @PostMapping("/queryByCondition")
    public ResponseResult queryByCondition(TsRoleSearchVo tsRoleSearchVo){
        ResponseResult responseResult = new ResponseResult();
        List list = tsRoleSearchService.queryByCondition(tsRoleSearchVo);
        return responseResult;
    }


}