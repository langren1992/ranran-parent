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
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-08-12 16:22:22.
*/
@RestController
public class TsCompanyRestController extends RestBaseController{

    @Autowired
    private TsCompanyService tsCompanyService;

    /**
     * 根据条件查询功能和排序功能
     *
     * @param tsCompany
     * @return PageInfo
     */
    @RequestMapping("/tsCompany/selectByCondition.html")
    public ResponseResult selectByCondition(TsCompany tsCompany){
        Example example = new Example(TsCompany.class);
        example.orderBy(tsCompany.getSort()).asc();
        return this.listResult(tsCompanyService.selectByCondition(example));
    }


    @RequestMapping("/tsCompany/loadCompanyDet.html")
    public ResponseResult loadCompanyDet(TsDept tsDept){
        return this.listResult(tsCompanyService.loadCompanyDet(tsDept));
    }

    /**
     * 批量新增或者更新操作
     *
     * @param tsCompanys
     * @return ResponseResult
     */
    @RequestMapping(value = "/tsCompany/saveBatch.html",method = RequestMethod.POST)
    public ResponseResult saveBatch(@RequestBody List<TsCompany> tsCompanys){
        return super.saveResult(tsCompanyService.saveBatch(tsCompanys));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsCompanys
     * @return ResponseResult
     */
    @RequestMapping("/tsCompany/updateBatch.html")
    public ResponseResult updateBatchByEnOrDis(@RequestBody List<TsCompany> tsCompanys){
        return super.optResult(tsCompanyService.updateBatch(tsCompanys));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsCompanys
     * @return ResponseResult
     */
    @RequestMapping("/tsCompany/deleteBatch.html")
    public ResponseResult deleteBatch(List<TsCompany> tsCompanys){
        return super.deleteResult(tsCompanyService.deleteBatchByIds(tsCompanys));
    }
}
