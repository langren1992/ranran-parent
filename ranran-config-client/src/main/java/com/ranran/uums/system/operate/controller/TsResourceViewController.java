package com.ranran.uums.system.operate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zengrui on 2017/7/9.
 */
@Controller
public class TsResourceViewController {

    @RequestMapping(value = "/tsResource.html")
    public String show() {
        return "system/tsResource.html";
    }
}
