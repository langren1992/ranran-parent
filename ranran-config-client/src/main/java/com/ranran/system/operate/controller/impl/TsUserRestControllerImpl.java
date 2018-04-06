package com.ranran.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.system.operate.controller.TsUserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/9.
 */
@RestController(value = "TsUserRestController")
@RequestMapping("/tsUser")
public class TsUserRestControllerImpl extends RestBaseController implements TsUserRestController {

    @Autowired
    private TsUserRestController tsUserRestController;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectUser.html")
    public ResponseResult selectUser(HttpServletRequest request) {
        return tsUserRestController.selectUser(request);
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateUsers.html")
    public ResponseResult updateUsers(HttpServletRequest request) {
        return tsUserRestController.updateUsers(request);
    }

    /**
     * 初始化密码
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateUserPwd.html")
    public ResponseResult updateUserPwd(HttpServletRequest request) {
        return tsUserRestController.updateUserPwd(request);
    }
}
