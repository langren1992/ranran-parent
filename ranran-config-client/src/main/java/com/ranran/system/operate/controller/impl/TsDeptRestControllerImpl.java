package com.ranran.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.system.operate.controller.TsDeptRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/9.
 */
@RestController(value = "TsDeptRestController")
@RequestMapping("/tsDept")
public class TsDeptRestControllerImpl extends RestBaseController implements TsDeptRestController {

    @Autowired
    private TsDeptRestController tsDeptRestController;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectDept.html")
    public ResponseResult selectDept(HttpServletRequest request) {
        return tsDeptRestController.selectDept(request);
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateDepts.html")
    public ResponseResult updateDepts(HttpServletRequest request) {
        return tsDeptRestController.updateDepts(request);
    }
}
