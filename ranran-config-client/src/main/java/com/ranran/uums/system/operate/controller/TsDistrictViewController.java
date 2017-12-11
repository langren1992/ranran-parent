package com.ranran.uums.system.operate.controller;

import com.ranran.core.RestBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* Created by zengrui on 2017-08-21 03:07:30.
*/
@Controller
public class TsDistrictViewController extends RestBaseController{

    /**
     * 跳转主页面
     *
     * @return String
     */
    @RequestMapping(value = "/tsDistrict.html")
    public String login() {
        return "system/tsDistrict.html";
    }
}
