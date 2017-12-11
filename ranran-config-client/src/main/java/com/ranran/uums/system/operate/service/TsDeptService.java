package com.ranran.uums.system.operate.service;


import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.vo.TsCompanyDeptVo;

import java.util.List;

/**
* Created by zengrui on 2017-08-11 12:10:02.
*/
public interface TsDeptService {

    public TsDept selectOne(TsDept tsDept);

    public List<TsDept> select(TsDept tsDept);

    /**
     * 插入空
     * */
    public int insert(TsDept tsDept);

    /**
     * 插入不为空的
     * */
    public int insertSelective(TsDept tsDept);

    public int insertBatch(List<TsDept> tsDepts);

    public int updateByPrimaryKey(TsDept tsDept) throws ServiceException;


    public int updateByPrimaryKeySelective(TsDept tsDept) throws ServiceException;


    public int updateBatch(List<TsDept> tsDepts);

    public int deleteByPrimaryKey(Object object);


    public int deleteBatchByIds(List<TsDept> tsDepts);

    public List<TsDept> selectByCondition(Object object);

    public int saveBatch(List<TsDept> tsDepts);

    public TsCompanyDeptVo loadCompanyDept(String principal);
}
