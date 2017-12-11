package com.ranran.uums.system.operate.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsDistrict;
import com.ranran.uums.system.operate.service.TsDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-08-21 03:07:30.
*/
@RestController
public class TsDistrictRestController extends RestBaseController{

    @Autowired
    private TsDistrictService tsDistrictService;

    @RequestMapping(value = "/tsDistrict/selectDistrict.html",method = RequestMethod.GET)
    public List<TsDistrict> selectDistrict(TsDistrict tsDistrict){
        Example example = new Example(TsDistrict.class);
        example.orderBy("distAdcode").asc();
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(tsDistrict.getDistParentCode())){
            criteria.andEqualTo("distParentCode",tsDistrict.getDistParentCode());
        }
        if(StringUtils.isNotEmpty(tsDistrict.getDistCode())){
            criteria.andEqualTo("distCode",tsDistrict.getDistCode());
        }
        criteria.andEqualTo("distLevel",tsDistrict.getDistLevel());
        return tsDistrictService.selectByExample(example);
    }

    /**
     * 根据条件查询功能和排序功能
     *
     * @param tsDistrict
     * @return PageInfo
     */
    @RequestMapping("/tsDistrict/selectByCondition.html")
    public PageInfo selectByCondition(TsDistrict tsDistrict){
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(tsDistrict.getPage(),tsDistrict.getRows());
        Example example = new Example(TsDistrict.class);
        if(StringUtils.isNotEmpty(tsDistrict.getOrder()) && StringUtils.isNotEmpty(tsDistrict.getSort())) {
            if("DESC".equalsIgnoreCase(tsDistrict.getOrder())){
                example.orderBy(tsDistrict.getSort()).desc();
            }else {
                example.orderBy(tsDistrict.getSort()).asc();
            }
        }
        List<TsDistrict> tsDistricts = tsDistrictService.selectByCondition(example);
        pageInfo.setList(tsDistricts);
        pageInfo.setTotal(((Page<TsDistrict>) tsDistricts).getTotal());
        return pageInfo;
    }

    /**
     * 批量新增或者更新操作
     *
     * @param tsDistricts
     * @return ResponseResult
     */
    @RequestMapping("/tsDistrict/saveBatch.html")
    public ResponseResult saveBatch(List<TsDistrict> tsDistricts){
        return super.saveResult(tsDistrictService.saveBatch(tsDistricts));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsDistricts
     * @return ResponseResult
     */
    @RequestMapping("/tsDistrict/updateBatch.html")
    public ResponseResult updateBatchByEnOrDis(List<TsDistrict> tsDistricts){
        return super.optResult(tsDistrictService.updateBatch(tsDistricts));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsDistricts
     * @return ResponseResult
     */
    @RequestMapping("/tsDistrict/deleteBatch.html")
    public ResponseResult deleteBatch(List<TsDistrict> tsDistricts){
        return super.deleteResult(tsDistrictService.deleteBatchByIds(tsDistricts));
    }
}
