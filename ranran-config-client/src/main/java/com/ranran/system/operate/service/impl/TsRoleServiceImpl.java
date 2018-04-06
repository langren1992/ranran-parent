package com.ranran.system.operate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.system.mapper.*;
import com.ranran.system.model.*;
import com.ranran.system.operate.vo.*;
import com.ranran.system.operate.service.TsRoleService;
import com.ranran.system.operate.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 角色新增、保存、启用、停用
 * 角色资源关联
 * 角色用户关联
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
        criteria.andNotEqualTo("roleStatus",2);
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
        //取出所有需要标记的资源编码
        Map<String,RaRoleResource> allResNo = new HashMap<String, RaRoleResource>(raRoleResourceList.size());
        //取出所有父及编码
        Map<String,RaRoleResource> parentResNoMap = new HashMap<String, RaRoleResource>(tsResourcesList.size());
        for (int j = 0, size = raRoleResourceList.size(); j < size; j++){
            allResNo.put(raRoleResourceList.get(j).getRrResourceNo(),raRoleResourceList.get(j));
        }
        for (int i = 0, size = tsResourcesList.size(); i < size; i++){
            parentResNoMap.put(tsResourcesList.get(i).getResParentNo(),null);
        }
        TsRoleResourceVo tsRoleResourceTreeVo = null;
        //标记后的所有菜单
        List<TsRoleResourceVo> tsRoleResourceTreeVoList = new ArrayList<TsRoleResourceVo>(tsResourcesList.size());
        for (int i = 0,size = tsResourcesList.size(); i < size; i++){
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
     * @param tsRoleResPermiVo 权限视图类
     * @return 权限视图列表
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
        TsRoleResPermiVo tsRoleResPermiTmp;
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
     * @param tsRoleUserVo 角色用户前台视图
     * @return 角色的用户列表
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
        // TODO: 2018/1/18 曾睿 添加保存、启用、停用
        // 新增列表
        List<TsRole> insertList = new ArrayList<TsRole>();
        // 更新列表
        List<TsRole> updateList = new ArrayList<TsRole>();
        TsRole tsRole;
        for (int i = 0,size = tsRoleUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsRoleUpdateVos.get(i).getRoleId() == null){
                tsRole = new TsRole();
                BeanUtils.copyProperties(tsRoleUpdateVos.get(i),tsRole);
                insertList.add(tsRole);
            } else {
                tsRole = new TsRole();
                BeanUtils.copyProperties(tsRoleUpdateVos.get(i),tsRole);
                updateList.add(tsRole);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i += tsRoleMapper.insertBatch(insertList);
        }
        if (updateList.size()>0){
            i += tsRoleMapper.updateBatch(updateList);
        }
        return i;
    }

    /**
     * 生成角色资源、权限关联关系
     *
     * @param tsRoleResRalVos
     * @return
     */
    @Override
    public int optRoleResRal(List<TsRoleResourceVo> tsRoleResRalVos) {
        // TODO: 2018/1/18 曾睿 生成角色资源、权限关联关系
        RaRoleResource raRoleResource = new RaRoleResource();
        raRoleResource.setRrRoleNo(tsRoleResRalVos.get(0).getRoleNo());
        //原角色资源集合
        List<RaRoleResource> raRoleResourcesOld = raRoleResourceMapper.select(raRoleResource);
        // 所有原始资源 菜单和按钮
        Set<String> resourceNoOldSet = new HashSet<String>();
        for (int i = 0,size = raRoleResourcesOld.size(); i < size; i++) {
            resourceNoOldSet.add(raRoleResourcesOld.get(i).getRrResourceNo());
        }
        Example example = new Example(TsResource.class);
        // 添加菜单查询条件
        example.createCriteria().andIn("resNo",resourceNoOldSet).andEqualTo("resType",1);
        List<TsResource> tsResourcesMenuOld = tsResourceMapper.selectByExample(example);
        resourceNoOldSet.clear();
        // 所有原始资源 菜单
        for (int i = 0,size = tsResourcesMenuOld.size(); i < size; i++) {
            resourceNoOldSet.add(tsResourcesMenuOld.get(i).getResNo());
        }
        // 获取最新资源 菜单
        Set<String> resourceNoNewSet = new HashSet<String>();
        for (TsRoleResourceVo tsRoleResourceVo:  tsRoleResRalVos) {
            resourceNoNewSet.add(tsRoleResourceVo.getResNo());
        }
        // 获取 原始菜单 新菜单 的差集 此元素为删除菜单
        Set<String> resourceNoDeleteSet = new HashSet<String>();
        resourceNoDeleteSet.addAll(resourceNoOldSet);
        resourceNoDeleteSet.removeAll(resourceNoNewSet);

        // 获取 新菜单 旧菜单 的差集 此元素为新增菜单
        Set<String> resourceNoInsertSet = new HashSet<String>();
        resourceNoInsertSet.addAll(resourceNoNewSet);
        resourceNoInsertSet.removeAll(resourceNoOldSet);

        List<RaRoleResource> raRoleResourcesInsert = new ArrayList<RaRoleResource>();
        //建立关联关系
        for (TsRoleResourceVo tsRoleResourceVo:  tsRoleResRalVos) {
            if(resourceNoInsertSet.contains(tsRoleResourceVo.getResNo())){
                raRoleResource = new RaRoleResource();
                raRoleResource.setRrRoleNo(tsRoleResourceVo.getRoleNo());
                raRoleResource.setRrResourceNo(tsRoleResourceVo.getResNo());
                raRoleResource.setChecked(tsRoleResourceVo.getChecked());
                raRoleResource.setCheckState(tsRoleResourceVo.getCheckState());
                raRoleResourcesInsert.add(raRoleResource);
            }
        }
        int i = 0;
        if (resourceNoDeleteSet.size() >=1){
            example = new Example(RaRoleResource.class);
            example.createCriteria().andEqualTo("rrRoleNo",tsRoleResRalVos.get(0).getRoleNo()).andIn("rrResourceNo",resourceNoDeleteSet);
            i += raRoleResourceMapper.deleteByExample(example);
        }
        if (raRoleResourcesInsert.size() >=1){
            i += raRoleResourceMapper.insertBatch(raRoleResourcesInsert);
        }
        return i;
    }

    /**
     * 生成角色用户关联关系
     *
     * @param tsRoleUserRalVo 角色用户操作视图
     * @return 操作数据条数
     */
    @Override
    public int optRoleUserRal(TsRoleUserRalVo tsRoleUserRalVo) {
        // TODO: 2018/1/18 曾睿 添加角色用户操作逻辑
        int i = 0;
        //存放add、delete操作需要添加的对象
        List<RaUserRole> raUserRoles = new ArrayList<RaUserRole>();
        //获取本次操作的用户信息
        List<TsUser> users = tsRoleUserRalVo.getUsers();
        //获取本次操作的角色编号
        String roleNo = tsRoleUserRalVo.getRoleNo();
        RaUserRole raUserRole;
        Set<String> userNoDeleteSet = new HashSet<String>(users.size());
        for (int j = 0,size = users.size(); j < size; j++) {
            //创建角色用户关联信息
            raUserRole = new RaUserRole();
            raUserRole.setUrRoleNo(roleNo);
            raUserRole.setUrUserNo(users.get(j).getUserNo());
            raUserRoles.add(raUserRole);
            userNoDeleteSet.add(users.get(j).getUserNo());
        }
        //判断 新增（ADD）、删除（DELETE）
        if (RoleUserOptEnum.ADD.name.equalsIgnoreCase(tsRoleUserRalVo.getOptType())){
            if (raUserRoles.size()>0){
                i += raUserRoleMapper.insertBatch(raUserRoles);
            }
        }else if(RoleUserOptEnum.DELETE.name.equalsIgnoreCase(tsRoleUserRalVo.getOptType())){
            if (userNoDeleteSet.size()>0){
                Example example = new Example(RaUserRole.class);
                example.createCriteria().andEqualTo("urRoleNo",roleNo).andIn("urUserNo",userNoDeleteSet);
                i += raUserRoleMapper.deleteByExample(example);
            }
        }
        return i;
    }

    /**
     * 删除角色
     *
     * @param tsRoleDeleteVo 角色删除前台视图
     * @return 删除结果
     */
    @Override
    public int deleteRole(TsRoleDeleteVo tsRoleDeleteVo) {
        // TODO: 2018/1/18 曾睿 添加角色删除逻辑
        TsRole tsRole = new TsRole();
        BeanUtils.copyProperties(tsRoleDeleteVo,tsRole);
        return tsRoleMapper.deleteByPrimaryKey(tsRole);
    }

    /**
     * 生成角色权限关联关系
     *
     * @param tsRoleResourceVos 角色权限关系视图
     * @return int
     */
    @Override
    public int optRoleResPermiRal(List<TsRoleResourceVo> tsRoleResourceVos) {
        // TODO: 2018/1/18 曾睿 角色权限关系视图
        TsResource tsResource = new TsResource();
        tsResource.setResParentNo(tsRoleResourceVos.get(0).getResParentNo());
        List<TsResource> tsResourcesList = tsResourceMapper.select(tsResource);
        Set<String> resNoSet = new HashSet<String>();
        for (int i = 0,size = tsResourcesList.size(); i < size; i++) {
            resNoSet.add(tsResourcesList.get(i).getResNo());
        }

        List<RaRoleResource> raRoleResourcesInsert = new ArrayList<RaRoleResource>();
        RaRoleResource raRoleResource;
        //建立关联关系
        for (TsRoleResourceVo tsRoleResourceVo:  tsRoleResourceVos) {
            raRoleResource = new RaRoleResource();
            if(tsRoleResourceVo.getResId()==null){
                raRoleResource.setRrRoleNo(tsRoleResourceVo.getRoleNo());
                raRoleResource.setRrResourceNo(tsRoleResourceVo.getResNo());
                raRoleResource.setChecked(tsRoleResourceVo.getChecked());
                raRoleResource.setCheckState(tsRoleResourceVo.getCheckState());
                raRoleResourcesInsert.add(raRoleResource);
            }
        }
        int i = 0;
        if(resNoSet.size() >=1){
            // 查询删除关联关系
            Example example = new Example(RaRoleResource.class);
            example.createCriteria().andEqualTo("rrRoleNo",tsRoleResourceVos.get(0).getRoleNo()).andIn("rrResourceNo",resNoSet);
            i+= raRoleResourceMapper.deleteByExample(example);
        }
        if (raRoleResourcesInsert.size() >=1){
            i+= raRoleResourceMapper.insertBatch(raRoleResourcesInsert);
        }
        return i;
    }

    /**
     * 用户角色信息操作枚举
     */
    private enum RoleUserOptEnum{

        ADD("ADD",1),DELETE("DELETE",2);

        public final String name;

        public final int code;

        RoleUserOptEnum(String name,int code){
            this.code = code;
            this.name = name;
        }

    }
}