package com.ranran.uums.system.operate.service;


import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsUser;

import java.util.List;

/**
* Created by zengrui on 2017-07-25 21:46:14.
*/
public interface TsUserService {

    public TsUser selectOne(TsUser tsUser);

    public List<TsUser> select(TsUser tsUser);

    /**
     * 插入空
     * */
    public int insert(TsUser tsUser);

    /**
     * 插入不为空的
     * */
    public int insertSelective(TsUser tsUser);

    public int insertBatch(List<TsUser> tsUsers);

    public int updateByPrimaryKey(TsUser tsUser) throws ServiceException;


    public int updateByPrimaryKeySelective(TsUser tsUser) throws ServiceException;


    public int updateBatch(List<TsUser> tsUsers);

    public int deleteByPrimaryKey(Object object);


    public int deleteBatchByIds(List<TsUser> tsUsers);

    public List<TsUser> selectByCondition(Object object);

    int saveBatch(List<TsUser> tsUsers);
}
