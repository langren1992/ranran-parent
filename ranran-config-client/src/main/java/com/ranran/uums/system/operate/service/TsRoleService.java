package com.ranran.uums.system.operate.service;

import com.github.pagehelper.PageInfo;
import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsRole;
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
    public List<TsRole> selectRoleUser(TsRoleUserVo tsRoleUserVo);

    /**
     * 查询不属于角色的用户信息
     * @param tsRoleNotUserVo
     * @return
     */
    public List<TsRole> selectRoleNotUser(TsRoleNotUserVo tsRoleNotUserVo);

    /**
     * 批量更新角色信息
     * @param tsRoleBatchVo
     * @return
     */
    public Integer updateRoleBatch(TsRoleBatchVo tsRoleBatchVo);
}
