package com.ranran.uums.system.operate.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsDeptService;
import com.ranran.uums.system.operate.vo.TsDeptSearchVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 部门信息请求控制
 *
 * Created by zengrui on 2017-08-11 12:10:02.
 */
public interface TsDeptRestController {

    /**
     * 查询部门信息，生成树形菜单
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult selectDept(HttpServletRequest request);


    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    public ResponseResult updateDepts(HttpServletRequest request);


}
