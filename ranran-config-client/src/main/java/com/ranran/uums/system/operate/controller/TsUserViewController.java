package com.ranran.uums.system.operate.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-07-25 21:46:14.
*/
@Controller
public class TsUserViewController extends RestBaseController{

    @Autowired
    private TsUserService tsUserService;

    /**
     * 跳转主页面
     *
     * @return String
     */
    @RequestMapping(value = "/tsUser.html")
    public String login() {
        return "system/tsUser.html";
    }

}
