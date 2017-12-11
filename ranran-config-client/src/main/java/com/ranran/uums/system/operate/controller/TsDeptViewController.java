package com.ranran.uums.system.operate.controller;

import com.ranran.core.RestBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
* Created by zengrui on 2017-08-11 12:10:02.
*/
@Controller
public class TsDeptViewController extends RestBaseController{

    /**
     * 跳转主页面
     *
     * @return String
     */
    @RequestMapping(value = "/tsDept.html")
    public String login() {
        return "system/tsDept";
    }
}
