package com.ranran.uums.system.operate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.uums.system.mapper.*;
import com.ranran.uums.system.model.*;
import com.ranran.uums.system.operate.service.TsRoleService;
import com.ranran.uums.system.operate.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by zengrui on 2017/7/17.
 */
@Service
public class TsRoleServiceImpl implements TsRoleService {


    @Autowired
    private TsRoleMapper tsRoleMapper;

    @Autowired
    private TsResourceMapper tsResourceMapper;

    @Autowired
    private RaRoleResourceMapper raRoleResourceMapper;

    @Autowired
    private RaUserRoleMapper raUserRoleMapper;

    @Autowired
    private TsUserMapper tsUserMapper;

    /**
     * 根据查询查询
     *
     * @param tsRoleConditionVo
     * @return
     */
    @Override
    public PageInfo pageRoleCondition(TsRoleConditionVo tsRoleConditionVo) {
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(tsRoleConditionVo.getPage(),tsRoleConditionVo.getRows());
        Example example = new Example(TsRole.class);
        if(com.ranran.core.util.StringUtils.isNotEmpty(tsRoleConditionVo.getOrder()) && com.ranran.core.util.StringUtils.isNotEmpty(tsRoleConditionVo.getSort())) {
            if("DESC".equalsIgnoreCase(tsRoleConditionVo.getOrder())){
                example.orderBy(tsRoleConditionVo.getSort()).desc();
            }else {
                example.orderBy(tsRoleConditionVo.getSort()).asc();
            }
        }else {
            example.orderBy("modifyTime").desc();
        }
        Example.Criteria criteria = example.createCriteria();
        if(com.ranran.core.util.StringUtils.isNotEmpty(tsRoleConditionVo.getRoleNo())){
            criteria.andEqualTo("roleNo",tsRoleConditionVo.getRoleNo());
        }
        if(com.ranran.core.util.StringUtils.isNotEmpty(tsRoleConditionVo.getRoleName())){
            criteria.andEqualTo("roleName",tsRoleConditionVo.getRoleName());
        }
        if(com.ranran.core.util.StringUtils.isNotEmpty(tsRoleConditionVo.getRoleStatus())){
            criteria.andEqualTo("roleStatus",tsRoleConditionVo.getRoleStatus());
        }
        List<TsRole> tsRoles = tsRoleMapper.selectByExample(example);
        pageInfo.setList(tsRoles);
        pageInfo.setTotal(((Page<TsRole>) tsRoles).getTotal());
        return pageInfo;
    }

    /**
     * 查询所有关联上的资源信息
     * @param tsRoleResourceVo
     * @return
     */
    @Override
    public List<TsRoleResourceVo> selectRoleResource(TsRoleResourceVo tsRoleResourceVo) {
        RaRoleResource raRoleResource = new RaRoleResource();
        raRoleResource.setRrRoleNo(tsRoleResourceVo.getRoleNo());
        //需要标记的菜单
        List<RaRoleResource> raRoleResourceList = raRoleResourceMapper.select(raRoleResource);
        TsResource tsResource = new TsResource();
        tsResource.setResType(1);
        //查询所有菜单
        List<TsResource> tsResourcesList = tsResourceMapper.select(tsResource);
        //标记后的所有菜单
        List<TsRoleResourceVo> tsRoleResourceTreeVoList = new ArrayList<TsRoleResourceVo>();
        //取出所有需要标记的资源编码
        Map<String,RaRoleResource> allResNo = new HashMap<String, RaRoleResource>();
        //取出所有父及编码
        Map<String,RaRoleResource> parentResNoMap = new HashMap<String, RaRoleResource>();
        for (int j = 0; j < raRoleResourceList.size(); j++){
            allResNo.put(raRoleResourceList.get(j).getRrResourceNo(),raRoleResourceList.get(j));
        }
        for (int i = 0; i < tsResourcesList.size(); i++){
            parentResNoMap.put(tsResourcesList.get(i).getResParentNo(),null);
        }
        TsRoleResourceVo tsRoleResourceTreeVo = null;
        for (int i = 0; i < tsResourcesList.size(); i++){
            tsRoleResourceTreeVo = new TsRoleResourceVo();
            BeanUtils.copyProperties(tsResourcesList.get(i),tsRoleResourceTreeVo);
            //包含则表示需要标记
            if (allResNo.containsKey(tsRoleResourceTreeVo.getResNo()) && !parentResNoMap.containsKey(tsRoleResourceTreeVo.getResNo())){
                tsRoleResourceTreeVo.setChecked(allResNo.get(tsRoleResourceTreeVo.getResNo()).getChecked());
                tsRoleResourceTreeVo.setCheckState(allResNo.get(tsRoleResourceTreeVo.getResNo()).getCheckState());
            }
            tsRoleResourceTreeVoList.add(tsRoleResourceTreeVo);
        }
        return tsRoleResourceTreeVoList;
    }

    /**
     * 查询资源权限信息
     *
     * @param tsRoleResPermiVo
     * @return
     */
    @Override
    public List<TsRoleResPermiVo> selectRoleResPermi(TsRoleResPermiVo tsRoleResPermiVo) {
        TsResource tsResource = new TsResource();
        //查询按钮
        tsResource.setResType(2);
        tsResource.setResParentNo(tsRoleResPermiVo.getResNo());
        //查询选择资源下的按钮
        List<TsResource> tsResourcesList = tsResourceMapper.select(tsResource);
        RaRoleResource raRoleResource = new RaRoleResource();
        raRoleResource.setRrRoleNo(tsRoleResPermiVo.getRoleNo());
        //查询角色下的所有资源信息
        List<RaRoleResource> raRoleResourceList = raRoleResourceMapper.select(raRoleResource);
        Map<String,RaRoleResource> selectResMap = new HashMap<String, RaRoleResource>();
        for (RaRoleResource raRoleResourceTmp: raRoleResourceList) {
            selectResMap.put(raRoleResourceTmp.getRrResourceNo(),null);
        }
        TsRoleResPermiVo tsRoleResPermiTmp = null;
        List<TsRoleResPermiVo> tsRoleResPermiList = new ArrayList<TsRoleResPermiVo>();
        for (TsResource tsResourceTmp: tsResourcesList) {
            tsRoleResPermiTmp = new TsRoleResPermiVo();
            BeanUtils.copyProperties(tsResourceTmp,tsRoleResPermiTmp);
            //控制需要勾选的数据
            if (selectResMap.containsKey(tsResourceTmp.getResNo())){
                tsRoleResPermiTmp.setChecked(true);
            }
            tsRoleResPermiList.add(tsRoleResPermiTmp);
        }
        return tsRoleResPermiList;
    }

    /**
     * 查询角色角色用户信息
     *
     * @param tsRoleUserVo
     * @return
     */
    @Override
    public List<TsUser> selectRoleUser(TsRoleUserVo tsRoleUserVo) {
        RaUserRole raUserRole = new RaUserRole();
        raUserRole.setUrRoleNo(tsRoleUserVo.getRoleNo());
        //使用角色用户关联关系表查询用户编码
        List<RaUserRole> raUserRoles = raUserRoleMapper.select(raUserRole);
        Map<String,Object> userNoMap = new HashMap<String,Object>();
        List<TsUser> tsUsers = new ArrayList<TsUser>();
        //没有用户编码查询为空
        if (raUserRoles.size() > 0){
            for (RaUserRole userRole: raUserRoles) {
                userNoMap.put(userRole.getUrUserNo(),null);
            }
            Example example = new Example(TsUser.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("userNo",userNoMap.keySet());
            tsUsers = tsUserMapper.selectByExample(example);
        }
        return tsUsers;
    }

    /**
     * 查询不属于角色的用户信息
     *
     * @param tsRoleNotUserVo
     * @return
     */
    @Override
    public List<TsUser> selectRoleNotUser(TsRoleNotUserVo tsRoleNotUserVo) {
        RaUserRole raUserRole = new RaUserRole();
        raUserRole.setUrRoleNo(tsRoleNotUserVo.getRoleNo());
        //使用角色用户关联关系表查询用户编码
        List<RaUserRole> raUserRoles = raUserRoleMapper.select(raUserRole);
        Map<String,Object> userNoMap = new HashMap<String,Object>();
        Example example = new Example(TsUser.class);
        Example.Criteria criteria = example.createCriteria();
        //没有用户编码查询全表
        if (raUserRoles.size() > 0){
            for (RaUserRole userRole: raUserRoles) {
                userNoMap.put(userRole.getUrUserNo(),null);
            }
            criteria.andNotIn("userNo",userNoMap.keySet());
        }
        return tsUserMapper.selectByExample(example);
    }

    /**
     * 批量更新、更新、启用、停用角色信息
     *
     * @param tsRoleUpdateVos
     * @return
     */
    @Override
    public Integer updateRoleBatch(List<TsRoleUpdateVo> tsRoleUpdateVos) {

        return null;
    }

    /**
     * 生成角色资源、权限关联关系
     *
     * @param tsRoleResRalVo
     * @return
     */
    @Override
    public int optRoleResRal(TsRoleResRalVo tsRoleResRalVo) {
        RaRoleResource raRoleResource = new RaRoleResource();
        raRoleResource.setRrRoleNo(tsRoleResRalVo.getRoleNo());
        //删除之前所有关联关系
        raRoleResourceMapper.delete(raRoleResource);
        List<RaRoleResource> addRoleResources = new ArrayList<RaRoleResource>();
        //建立关联关系
        for (TsResource tsResource:  tsRoleResRalVo.getResources()) {
            raRoleResource = new RaRoleResource();
            raRoleResource.setRrRoleNo(tsRoleResRalVo.getRoleNo());
            raRoleResource.setRrResourceNo(tsResource.getResNo());
            raRoleResource.setChecked(tsRoleResRalVo.getChecked());
            raRoleResource.setCheckState(tsRoleResRalVo.getCheckState());
            addRoleResources.add(raRoleResource);
        }
        return raRoleResourceMapper.insertBatch(addRoleResources);
    }

    /**
     * 生成角色用户关联关系
     *
     * @param tsRoleUserRalVo
     * @return
     */
    @Override
    public int optRoleUserRal(TsRoleUserRalVo tsRoleUserRalVo) {
        return 0;
    }

    /**
     * 删除角色
     *
     * @param tsRoleDeleteVo
     * @return
     */
    @Override
    public int deleteRole(TsRoleDeleteVo tsRoleDeleteVo) {
        return 0;
    }

}