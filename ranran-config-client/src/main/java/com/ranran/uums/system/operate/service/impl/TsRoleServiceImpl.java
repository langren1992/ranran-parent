package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.exception.ServiceException;
import com.ranran.core.shiro.util.StringUtils;
import com.ranran.uums.system.mapper.RaRoleResourceMapper;
import com.ranran.uums.system.mapper.TsResourceMapper;
import com.ranran.uums.system.mapper.TsRoleMapper;
import com.ranran.uums.system.model.RaRoleResource;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsRole;
import com.ranran.uums.system.operate.service.TsRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * @descripte
     * @param tsRole
     * @return TsRole
     */
    @Override
    public TsRole selectOne(TsRole tsRole){
        return tsRoleMapper.selectOne(tsRole);
    }

    @Override
    public List<TsRole> select(TsRole tsRole){
        return tsRoleMapper.select(tsRole);
    }

    /**
     * 插入空
     * */
    @Override
    public int insert(TsRole tsRole){
        return tsRoleMapper.insert(tsRole);
    }

    /**
     * 插入不为空的
     * */
    @Override
    public int insertSelective(TsRole tsRole){
        return tsRoleMapper.insertSelective(tsRole);
    }

    /**
     *
     * @param tsRoles
     * @return
     */
    @Override
    public int insertBatch(List<TsRole> tsRoles){
        return tsRoleMapper.insertBatch(tsRoles);
    }

    @Override
    public int updateByPrimaryKey(TsRole tsRole) throws ServiceException {
        if (StringUtils.isNotEmpty(tsRole.getRoleId())){
            fullUpdateDefaultInfo(tsRole);
            return tsRoleMapper.updateByPrimaryKey(tsRole);
        }
        throw new ServiceException(TsRoleServiceImpl.class.toString()+"出现异常，异常编号"+001);

    }

    @Override
    public int updateByPrimaryKeySelective(TsRole tsRole) throws ServiceException {
        if (StringUtils.isNotEmpty(tsRole.getRoleId())){
            fullUpdateDefaultInfo(tsRole);
            return tsRoleMapper.updateByPrimaryKeySelective(tsRole);
        }
        throw new ServiceException(TsRoleServiceImpl.class.toString()+"出现异常，异常编号"+001);
    }

    @Override
    public int updateBatch(List<TsRole> tsRoles){
        TsRole tsRole = null;
        for (int i = 0,size = tsRoles.size(); i < size; i++) {
            tsRole = tsRoles.get(i);
            fullUpdateDefaultInfo(tsRole);
        }
        return tsRoleMapper.updateBatch(tsRoles);
    }


    @Override
    public int deleteByPrimaryKey(Object object){
        return  tsRoleMapper.deleteByPrimaryKey(object);
    }

    @Override
    public int deleteBatchByIds(List<TsRole> tsRoles){
        return  tsRoleMapper.deleteBatchByIds(tsRoles);
    }

    @Override
    public List<TsRole> selectByCondition(Object object){
        return tsRoleMapper.selectByExample(object);
    }

    @Override
    public int saveBatch(List<TsRole> tsRoles) {
        List<TsRole> tsRolesInert = new ArrayList<TsRole>();
        List<TsRole> tsRolesUpdate = new ArrayList<TsRole>();
        for (TsRole tsRole: tsRoles) {
            /**
             * 没有主键是新增
             */
            if (StringUtils.isEmpty(tsRole.getRoleId())){
                fullInertDefaultInfo(tsRole);
                tsRolesInert.add(tsRole);
            }else{
                fullUpdateDefaultInfo(tsRole);
                tsRolesUpdate.add(tsRole);
            }
        }
        int i = 0;
        if (tsRolesInert.size() >= 1){
            i = tsRoleMapper.insertBatch(tsRolesInert);
        }
        if (tsRolesUpdate.size() >=1){
            i = i + tsRoleMapper.updateBatch(tsRolesUpdate);
        }
        return i;
    }

    /**
     * 添加插入实体时其他信息
     * @param tsRole
     * @return
     */
    private void fullInertDefaultInfo(TsRole tsRole) {
//        tsRole.setRoleId(IdWorkerGen.nextID());
        tsRole.setRecVer(1);
        tsRole.setCreator((String) SecurityUtils.getSubject().getPrincipal());
        tsRole.setCreateTime(new Date());
        tsRole.setModifier((String) SecurityUtils.getSubject().getPrincipal());
        tsRole.setModifyTime(new Date());
    }

    /**
     * 添加插入实体时其他信息
     * @param tsRole
     * @return
     */
    private void fullUpdateDefaultInfo(TsRole tsRole) {
        tsRole.setRecVer(tsRole.getRecVer()+1);
        tsRole.setModifier((String) SecurityUtils.getSubject().getPrincipal());
        tsRole.setModifyTime(new Date());
    }

    /**
     * 查询所有关联上的资源信息
     * @param tsRole
     * @return
     */
    @Override
    public List selectRoleResource(TsRole tsRole) {
        RaRoleResource raRoleResource = new RaRoleResource();
        raRoleResource.setRrRoleNo(tsRole.getRoleNo());
        List<RaRoleResource> raRoleResourceList = raRoleResourceMapper.select(raRoleResource);
        /*查询所有需要的资源信息*/
        List<TsResource> tsResourcesList = tsResourceMapper.select(null);
        for (int i = 0; i < raRoleResourceList.size(); i++){
            for (int j = 0; j < tsResourcesList.size(); j++){
                if (tsResourcesList.get(j).getResNo().equals(raRoleResourceList.get(i).getRrResourceNo())){
//                    tsResourcesList.get(j).setChecked(raRoleResourceList.get(i).getChecked());
//                    tsResourcesList.get(j).setCheckState(raRoleResourceList.get(i).getCheckState());
                }
            }
        }
        return tsResourcesList;
    }

    /**
     * 查询所有满足条件的资源信息
     * @return
     */
    @Override
    public List selectResource() {
        return tsResourceMapper.select(null);
    }

    /**
     * 查询角色下的用户信息
     * @param tsRole
     * @return
     */
    @Override
    public List selectRoleUser(TsRole tsRole) {
        return tsRoleMapper.selectRoleUser(tsRole);
    }
}
