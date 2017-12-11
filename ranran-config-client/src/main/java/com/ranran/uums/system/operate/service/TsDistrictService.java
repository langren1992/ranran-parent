package com.ranran.uums.system.operate.service;


import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsDistrict;

import java.util.List;

/**
* Created by zengrui on 2017-08-21 03:07:30.
*/
public interface TsDistrictService {

    public TsDistrict selectOne(TsDistrict tsDistrict);

    public List<TsDistrict> select(TsDistrict tsDistrict);

    /**
     * 插入空
     * */
    public int insert(TsDistrict tsDistrict);

    /**
     * 插入不为空的
     * */
    public int insertSelective(TsDistrict tsDistrict);

    public int insertBatch(List<TsDistrict> tsDistricts);

    public int updateByPrimaryKey(TsDistrict tsDistrict) throws ServiceException;


    public int updateByPrimaryKeySelective(TsDistrict tsDistrict) throws ServiceException;


    public int updateBatch(List<TsDistrict> tsDistricts);

    public int deleteByPrimaryKey(Object object);


    public int deleteBatchByIds(List<TsDistrict> tsDistricts);

    public List<TsDistrict> selectByCondition(Object object);

    public int saveBatch(List<TsDistrict> tsDistricts);

    public List<TsDistrict> selectByExample(Object object);
}
