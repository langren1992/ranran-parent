package com.ranran.uums.system.operate.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsDeptService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-08-11 12:10:02.
*/
@RestController
public class TsDeptRestController extends RestBaseController{

    @Autowired
    private TsDeptService tsDeptService;

    @RequestMapping("/tsDept/loadCompanyDept.html")
    public ResponseResult loadCompanyDept(){
        return this.objectResult(tsDeptService.loadCompanyDept((String) SecurityUtils.getSubject().getPrincipal()));
    }


    /**
     * 根据条件查询功能和排序功能
     *
     * @param tsDept
     * @return PageInfo
     */
    @RequestMapping("/tsDept/selectByCondition.html")
    public PageInfo selectByCondition(TsDept tsDept){
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(tsDept.getPage(),tsDept.getRows());
        Example example = new Example(TsDept.class);
        if(StringUtils.isNotEmpty(tsDept.getOrder()) && StringUtils.isNotEmpty(tsDept.getSort())) {
            if("DESC".equalsIgnoreCase(tsDept.getOrder())){
                example.orderBy(tsDept.getSort()).desc();
            }else {
                example.orderBy(tsDept.getSort()).asc();
            }
        }
        List<TsDept> tsDepts = tsDeptService.selectByCondition(example);
        pageInfo.setList(tsDepts);
        pageInfo.setTotal(((Page<TsDept>) tsDepts).getTotal());
        return pageInfo;
    }

    /**
     * 批量新增或者更新操作
     *
     * @param tsDepts
     * @return ResponseResult
     */
    @RequestMapping("/tsDept/saveBatch.html")
    public ResponseResult saveBatch(@RequestBody List<TsDept> tsDepts){
        return super.saveResult(tsDeptService.saveBatch(tsDepts));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsDepts
     * @return ResponseResult
     */
    @RequestMapping("/tsDept/updateBatch.html")
    public ResponseResult updateBatchByEnOrDis(@RequestBody List<TsDept> tsDepts){
        return super.optResult(tsDeptService.updateBatch(tsDepts));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsDepts
     * @return ResponseResult
     */
    @RequestMapping("/tsDept/deleteBatch.html")
    public ResponseResult deleteBatch(List<TsDept> tsDepts){
        return super.deleteResult(tsDeptService.deleteBatchByIds(tsDepts));
    }
}
