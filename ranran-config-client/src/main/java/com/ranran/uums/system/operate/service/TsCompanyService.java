package com.ranran.uums.system.operate.service;



import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.model.TsCompany;
import com.ranran.uums.system.model.TsDept;

import java.util.List;

/**
* Created by zengrui on 2017-08-12 16:22:22.
*/
public interface TsCompanyService {

    public TsCompany selectOne(TsCompany tsCompany);

    public List<TsCompany> select(TsCompany tsCompany);

    /**
     * 插入空
     * */
    public int insert(TsCompany tsCompany);

    /**
     * 插入不为空的
     * */
    public int insertSelective(TsCompany tsCompany);

    public int insertBatch(List<TsCompany> tsCompanys);

    public int updateByPrimaryKey(TsCompany tsCompany) throws ServiceException;


    public int updateByPrimaryKeySelective(TsCompany tsCompany) throws ServiceException;


    public int updateBatch(List<TsCompany> tsCompanys);

    public int deleteByPrimaryKey(Object object);


    public int deleteBatchByIds(List<TsCompany> tsCompanys);

    public List<TsCompany> selectByCondition(Object object);

    public int saveBatch(List<TsCompany> tsCompanys);

    public List loadCompanyDet(TsDept tsDept);
}
