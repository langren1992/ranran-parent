package com.ranran.uums.system.operate.service;

import com.github.pagehelper.PageInfo;
import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsRole;
import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.vo.*;

import java.util.List;

/**
 * Created by zengrui on 2017/7/17.
 */
public interface TsRoleService {

    /**
     * 根据查询查询
     *
     * @param tsRoleConditionVo
     * @return
     */
    public PageInfo pageRoleCondition(TsRoleConditionVo tsRoleConditionVo);

    /**
     * 查询角色资源
     * @param tsRoleResourceVo
     * @return
     */
    public List<TsRoleResourceVo> selectRoleResource(TsRoleResourceVo tsRoleResourceVo);

    /**
     * 查询资源权限信息
     * @param tsRoleResPermiVo
     * @return
     */
    public List<TsRoleResPermiVo> selectRoleResPermi(TsRoleResPermiVo tsRoleResPermiVo);

    /**
     * 查询角色角色用户信息
     * @param tsRoleUserVo
     * @return
     */
    public List<TsUser> selectRoleUser(TsRoleUserVo tsRoleUserVo);

    /**
     * 查询不属于角色的用户信息
     * @param tsRoleNotUserVo
     * @return
     */
    public List<TsUser> selectRoleNotUser(TsRoleNotUserVo tsRoleNotUserVo);

    /**
     * 批量更新、更新、启用、停用角色信息
     * @param tsRoleUpdateVos
     * @return
     */
    public Integer updateRoleBatch(List<TsRoleUpdateVo> tsRoleUpdateVos);

    /**
     * 生成角色资源、权限关联关系
     * @param tsRoleResRalVo
     * @return
     */
    int optRoleResRal(TsRoleResRalVo tsRoleResRalVo);

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
}
