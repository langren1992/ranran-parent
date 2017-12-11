package com.ranran.uums.system.operate.controller;

import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.uums.system.model.TsCompany;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-08-12 16:22:22.
*/
@Controller
public class TsCompanyViewController extends RestBaseController{


    /**
     * 跳转主页面
     *
     * @return String
     */
    @RequestMapping(value = "/tsCompany.html")
    public String login() {
        return "system/tsCompany.html";
    }
}
