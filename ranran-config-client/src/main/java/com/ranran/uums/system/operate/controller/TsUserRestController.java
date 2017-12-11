package com.ranran.uums.system.operate.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
* Created by zengrui on 2017-07-25 21:46:14.
*/
@RestController
public class TsUserRestController extends RestBaseController{

    @Autowired
    private TsUserService tsUserService;

    /**
     * 根据条件查询功能和排序功能
     *
     * @param tsUser
     * @return PageInfo
     */
    @RequestMapping("/tsUser/selectByCondition.html")
    public PageInfo selectByCondition(TsUser tsUser){
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(tsUser.getPage(),tsUser.getRows());
        Example example = new Example(TsUser.class);
        if(StringUtils.isNotEmpty(tsUser.getOrder()) && StringUtils.isNotEmpty(tsUser.getSort())) {
            if("DESC".equalsIgnoreCase(tsUser.getOrder())){
                example.orderBy(tsUser.getSort()).desc();
            }else {
                example.orderBy(tsUser.getSort()).asc();
            }
        }
        List<TsUser> tsUsers = tsUserService.selectByCondition(example);
        pageInfo.setList(tsUsers);
        pageInfo.setTotal(((Page<TsUser>) tsUsers).getTotal());
        return pageInfo;
    }

    /**
     * 批量新增或者更新操作
     *
     * @param tsUsers
     * @return ResponseResult
     */
    @RequestMapping("/tsUser/saveBatch.html")
    public ResponseResult saveBatch(List<TsUser> tsUsers){
        return super.saveResult(tsUserService.saveBatch(tsUsers));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsUsers
     * @return ResponseResult
     */
    @RequestMapping("/tsUser/updateBatch.html")
    public ResponseResult updateBatchByEnOrDis(List<TsUser> tsUsers){
        return super.optResult(tsUserService.updateBatch(tsUsers));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsUsers
     * @return ResponseResult
     */
    @RequestMapping("/tsUser/deleteBatch.html")
    public ResponseResult deleteBatch(List<TsUser> tsUsers){
        return super.deleteResult(tsUserService.deleteBatchByIds(tsUsers));
    }
}
