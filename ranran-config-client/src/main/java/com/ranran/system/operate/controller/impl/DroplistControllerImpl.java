package com.ranran.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.system.operate.controller.DroplistController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController(value="DroplistController")
@RequestMapping(value = "/droplist")
public class DroplistControllerImpl implements DroplistController {

    protected Logger LOGGER = LoggerFactory.getLogger(DroplistControllerImpl.class);

    // controller本地控制实现
    @Autowired
    private DroplistController droplistController;


    /**
     * @param request HttpServletRequest
     * @return ResponseResult
     * @Title queryDropListData
     * @description 查询下拉列表数据
     * @author zengrui
     * @createTime 2018/4/6 21:36
     * @modifyTime 2018/4/6 21:36
     **/
    @Override
    @PostMapping(value = "/queryDropListData.html")
    public ResponseResult queryDropListData(HttpServletRequest request) {
        return droplistController.queryDropListData(request);
    }
}
