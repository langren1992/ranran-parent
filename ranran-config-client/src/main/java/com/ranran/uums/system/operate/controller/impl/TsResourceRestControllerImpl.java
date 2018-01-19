package com.ranran.uums.system.operate.controller.impl;

import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.operate.controller.TsResourceRestController;
import com.ranran.uums.system.operate.service.TsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/9.
 */
@RestController(value = "TsResourceRestController")
public class TsResourceRestControllerImpl extends RestBaseController implements TsResourceRestController {

    @Autowired
    private TsResourceService tsResourceService;

    @Autowired
    private TsResourceRestController tsResourceRestController;

    @GetMapping(value = "/res/resList.html")
    public ResponseResult selectTreeData(TsResource tsResource) {
        return this.listResult(tsResourceService.select(tsResource));
    }

    @GetMapping(value = "/res/resBtnList.html")
    public ResponseResult selectBtmData(TsResource tsResource) {
        return this.listResult(tsResourceService.select(tsResource));
    }

    @PostMapping(value = "/res/updateRes.html")
    public ResponseResult updateResData(HttpServletRequest request){
        return tsResourceRestController.updateResData(request);
    }
}
