package com.ranran.system.operate.service;

import com.github.pagehelper.PageInfo;
import com.ranran.system.operate.vo.*;
import com.ranran.system.model.TsUser;
import com.ranran.system.operate.vo.*;

import java.util.List;

/**
 *
 *  角色新增、保存、启用、停用
 *  角色资源关联
 *  角色用户关联
 *
 * @auth zengrui
 * @time 2017/7/17
 */
public interface TsRoleService {

    /**
     * 根据查询查询
     *
     * @param tsRoleConditionVo
     * @return
     */
    PageInfo pageRoleCondition(TsRoleConditionVo tsRoleConditionVo);

    /**
     * 查询角色资源
     * @param tsRoleResourceVo
     * @return
     */
    List<TsRoleResourceVo> selectRoleResource(TsRoleResourceVo tsRoleResourceVo);

    /**
     * 查询资源权限信息
     * @param tsRoleResPermiVo
     * @return
     */
    List<TsRoleResPermiVo> selectRoleResPermi(TsRoleResPermiVo tsRoleResPermiVo);

    /**
     * 查询角色角色用户信息
     * @param tsRoleUserVo
     * @return
     */
    List<TsUser> selectRoleUser(TsRoleUserVo tsRoleUserVo);

    /**
     * 查询不属于角色的用户信息
     * @param tsRoleNotUserVo
     * @return
     */
    List<TsUser> selectRoleNotUser(TsRoleNotUserVo tsRoleNotUserVo);

    /**
     * 批量更新、更新、启用、停用角色信息
     * @param tsRoleUpdateVos
     * @return
     */
    Integer updateRoleBatch(List<TsRoleUpdateVo> tsRoleUpdateVos);

    /**
     * 生成角色资源、权限关联关系
     * @param tsRoleResRalVo
     * @return
     */
    int optRoleResRal(List<TsRoleResourceVo> tsRoleResRalVo);

    /**
     * 生成角色用户关联关系
     * @param tsRoleUserRalVo
     * @return
     */
    int optRoleUserRal(TsRoleUserRalVo tsRoleUserRalVo);

    /**
     * 删除角色
     *
     * @param tsRoleDeleteVo
     * @return
     */
    int deleteRole(TsRoleDeleteVo tsRoleDeleteVo);

    /**
     * 生成角色权限关联关系
     *
     * @param tsRoleResourceVos 角色权限关系视图
     * @return int
     */
    int optRoleResPermiRal(List<TsRoleResourceVo> tsRoleResourceVos);
}
