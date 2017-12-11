package com.ranran.uums.system.operate.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.exception.ServiceException;
import com.ranran.core.util.StringUtils;
import com.ranran.uums.system.model.TsRole;
import com.ranran.uums.system.operate.service.TsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by zengrui on 2017/7/11.
 */
@RestController
public class TsRoleRestController extends RestBaseController {

    @Autowired
    private TsRoleService tsRoleService;

    /**
     * 根据条件查询功能和排序功能
     *
     * @param tsRole
     * @return PageInfo
     */
    @RequestMapping("/tsRole/selectByCondition.html")
    public ResponseResult selectByCondition(TsRole tsRole){
        ResponseResult resultBody = new ResponseResult();
        PageHelper.startPage(tsRole.getPage(),tsRole.getRows());
        Example example = new Example(TsRole.class);
        if(StringUtils.isNotEmpty(tsRole.getOrder()) && StringUtils.isNotEmpty(tsRole.getSort())) {
            if("DESC".equalsIgnoreCase(tsRole.getOrder())){
                example.orderBy(tsRole.getSort()).desc();
            }else {
                example.orderBy(tsRole.getSort()).asc();
            }
        }else {
            example.orderBy("modifyTime").desc();
        }
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(tsRole.getRoleNo())){
            criteria.andEqualTo("roleNo",tsRole.getRoleNo());
        }
        if(StringUtils.isNotEmpty(tsRole.getRoleName())){
            criteria.andEqualTo("roleName",tsRole.getRoleName());
        }
        if(StringUtils.isNotEmpty(tsRole.getRoleStatus())){
            criteria.andEqualTo("roleStatus",tsRole.getRoleStatus());
        }
        List<TsRole> tsRoles = tsRoleService.selectByCondition(example);
        if (tsRoles.size() >0){
            resultBody.setRows(tsRoles.toArray());
            resultBody.setTotal(((Page<TsRole>) tsRoles).getTotal());
        }else {
            resultBody.setRows(new Object[]{});
            resultBody.setTotal(((Page<TsRole>) tsRoles).getTotal());
        }
        return resultBody;
    }

    @RequestMapping("/tsRole/resource.html")
    public ResponseResult selectResource(){
        return super.listResult(tsRoleService.selectResource());
    }

    @RequestMapping("/tsRole/roleResource.html")
    public ResponseResult selectRoleResource(TsRole tsRole){
        return super.listResult(tsRoleService.selectRoleResource(tsRole));
    }

    @RequestMapping("/tsRole/roleUser.html")
    public ResponseResult selectRoleUser(TsRole tsRole){
        return super.listResult(tsRoleService.selectRoleUser(tsRole));
    }


    /**
     * 批量新增或者更新操作
     *
     * @param tsRoles
     * @return ResponseResult
     */
    @RequestMapping("/tsRole/saveBatch.html")
    public ResponseResult saveBatch(@RequestBody List<TsRole> tsRoles){
        return super.saveResult(tsRoleService.saveBatch(tsRoles));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsRoles
     * @return ResponseResult
     */
    @RequestMapping("/tsRole/updateBatch.html")
    public ResponseResult updateBatchByEnOrDis(@RequestBody List<TsRole> tsRoles) throws ServiceException {
        return super.optResult(tsRoleService.updateBatch(tsRoles));
    }

    /**
     * 启用或者停用操作
     *
     * @param tsRoles
     * @return ResponseResult
     */
    @RequestMapping("/tsRole/deleteBatch.html")
    public ResponseResult deleteBatch(@RequestBody List<TsRole> tsRoles){
        return super.deleteResult(tsRoleService.deleteBatchByIds(tsRoles));
    }


}
