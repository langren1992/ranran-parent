package com.ranran.uums.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsSystemControlRestController;
import com.ranran.uums.system.operate.service.TsResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统控制参数请求控制类
 * @creator zengrui 2018-01-30 10:13
 */
@RestController(value = "TsSystemControlRestController")
@RequestMapping("/tsSystemControl")
public class TsSystemControlRestControllerImpl extends RestBaseController implements TsSystemControlRestController {

    private static Logger LOGGER = LoggerFactory.getLogger(TsSystemControlRestControllerImpl.class);

    @Autowired
    private TsSystemControlRestController tsSystemControlRestController;

    /**
     * 查询系统控制参数信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectTsSystemControl.html")
    public ResponseResult selectTsSystemControl(HttpServletRequest request) {
        return tsSystemControlRestController.selectTsSystemControl(request);
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）系统控制参数
    *
    * @param request 请求参数
    * @return ResponseResult 响应结果
    */
    @Override
    @PostMapping("/updateTsSystemControls.html")
    public ResponseResult updateTsSystemControls(HttpServletRequest request) {
        return tsSystemControlRestController.updateTsSystemControls(request);
    }
}
