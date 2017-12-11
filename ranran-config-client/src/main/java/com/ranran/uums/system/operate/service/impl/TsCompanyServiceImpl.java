package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.exception.ServiceException;
import com.ranran.core.shiro.util.StringUtils;
import com.ranran.uums.system.mapper.TsCompanyMapper;
import com.ranran.uums.system.mapper.TsDeptMapper;
import com.ranran.uums.system.model.TsCompany;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* Created by zengrui on 2017-08-12 16:22:22.
*/
@Service
public class TsCompanyServiceImpl implements TsCompanyService {

    @Autowired
    private TsCompanyMapper tsCompanyMapper;

    @Autowired
    private TsDeptMapper tsDeptMapper;

    /**
     * @descripte
     * @param tsCompany
     * @return TsCompany
     */
    @Override
    public TsCompany selectOne(TsCompany tsCompany){
        return tsCompanyMapper.selectOne(tsCompany);
    }

    @Override
    public List<TsCompany> select(TsCompany tsCompany){
        return tsCompanyMapper.select(tsCompany);
    }

    /**
     * 插入空
     * */
    @Override
    public int insert(TsCompany tsCompany){
        return tsCompanyMapper.insert(tsCompany);
    }

    /**
     * 插入不为空的
     * */
    @Override
        public int insertSelective(TsCompany tsCompany){
        return tsCompanyMapper.insertSelective(tsCompany);
    }

    /**
     *
     * @param tsCompanys
     * @return
     */
    @Override
    public int insertBatch(List<TsCompany> tsCompanys){
        return tsCompanyMapper.insertBatch(tsCompanys);
    }

    @Override
    public int updateByPrimaryKey(TsCompany tsCompany) throws ServiceException {
        if (StringUtils.isNotEmpty(tsCompany.getOrgId())){
            return tsCompanyMapper.updateByPrimaryKey(tsCompany);
        }
        throw new ServiceException(TsCompanyServiceImpl.class.toString()+"出现异常，异常编号"+001);
    }

    @Override
    public int updateByPrimaryKeySelective(TsCompany tsCompany) throws ServiceException {
        if (StringUtils.isNotEmpty(tsCompany.getOrgId())){
            return tsCompanyMapper.updateByPrimaryKeySelective(tsCompany);
        }
        throw new ServiceException(TsCompanyServiceImpl.class.toString()+"出现异常，异常编号"+002);
    }

    @Override
    public int updateBatch(List<TsCompany> tsCompanys){
        return tsCompanyMapper.updateBatch(tsCompanys);
    }


    @Override
    public int deleteByPrimaryKey(Object object){
        return  tsCompanyMapper.deleteByPrimaryKey(object);
    }

    @Override
    public int deleteBatchByIds(List<TsCompany> tsCompanys){
        return  tsCompanyMapper.deleteBatchByIds(tsCompanys);
    }

    @Override
    public List<TsCompany> selectByCondition(Object object){
        return tsCompanyMapper.selectByExample(object);
    }

    @Override
    public int saveBatch(List<TsCompany> tsCompanys) {
        List<TsCompany> tsCompanysInert = new ArrayList<TsCompany>();
        List<TsCompany> tsCompanysUpdate = new ArrayList<TsCompany>();
        for (TsCompany tsCompany: tsCompanys) {
            /**
            * 没有主键是新增
            */
            if (StringUtils.isEmpty(tsCompany.getOrgId())){
//                tsCompany.setOrgId(IdWorkerGen.nextID());
                tsCompanysInert.add(tsCompany);
            }else{
                tsCompanysUpdate.add(tsCompany);
            }
        }
        int i = 0;
        if (tsCompanysInert.size() >= 1){
            i = tsCompanyMapper.insertBatch(tsCompanysInert);
        }
        if (tsCompanysUpdate.size() >=1){
            i = i + tsCompanyMapper.updateBatch(tsCompanysUpdate);
        }
        return i;
    }

    @Override
    public List loadCompanyDet(TsDept tsDept) {
        return tsDeptMapper.select(tsDept);
    }
}
