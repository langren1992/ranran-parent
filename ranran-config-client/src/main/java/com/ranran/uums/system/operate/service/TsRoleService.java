package com.ranran.uums.system.operate.service;

import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsRole;

import java.util.List;

/**
 * Created by zengrui on 2017/7/17.
 */
public interface TsRoleService {

    public TsRole selectOne(TsRole tsRole);

    public List<TsRole> select(TsRole tsRole);

    /**
     * 插入空
     * */
    public int insert(TsRole tsRole);

    /**
     * 插入不为空的
     * */
    public int insertSelective(TsRole tsRole);

    public int insertBatch(List<TsRole> tsRoles);

    public int updateByPrimaryKey(TsRole tsRole) throws ServiceException;


    public int updateByPrimaryKeySelective(TsRole tsRole) throws ServiceException;


    public int updateBatch(List<TsRole> tsRoles);

    public int deleteByPrimaryKey(Object object);


    public int deleteBatchByIds(List<TsRole> tsRoles);

    public List<TsRole> selectByCondition(Object object);

    public int saveBatch(List<TsRole> tsRoles);

    public List selectRoleResource(TsRole tsRole);

    public List selectResource();

    public List selectRoleUser(TsRole tsRole);
}
