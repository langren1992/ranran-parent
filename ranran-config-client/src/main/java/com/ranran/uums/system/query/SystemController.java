package com.ranran.uums.system.query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {


    @GetMapping("system/tsRole/operate")
    public String operateTsRole(){
        return "ui/system/tsRoleSearch.html";
    }
}
