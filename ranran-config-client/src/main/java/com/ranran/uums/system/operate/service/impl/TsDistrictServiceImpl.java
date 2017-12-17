package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.exception.ServiceException;
import com.ranran.core.shiro.util.StringUtils;
import com.ranran.uums.system.mapper.TsDistrictMapper;
import com.ranran.uums.system.model.TsDistrict;
import com.ranran.uums.system.operate.service.TsDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* Created by zengrui on 2017-08-21 03:07:30.
*/
@Service
public class TsDistrictServiceImpl implements TsDistrictService {

    @Autowired
    private TsDistrictMapper tsDistrictMapper;

    /**
     * @descripte
     * @param tsDistrict
     * @return TsDistrict
     */
    @Override
    public TsDistrict selectOne(TsDistrict tsDistrict){
        return tsDistrictMapper.selectOne(tsDistrict);
    }

    @Override
    public List<TsDistrict> select(TsDistrict tsDistrict){
        return tsDistrictMapper.select(tsDistrict);
    }

    /**
     * 插入空
     * */
    @Override
    public int insert(TsDistrict tsDistrict){
        return tsDistrictMapper.insert(tsDistrict);
    }

    /**
     * 插入不为空的
     * */
    @Override
        public int insertSelective(TsDistrict tsDistrict){
        return tsDistrictMapper.insertSelective(tsDistrict);
    }

    /**
     *
     * @param tsDistricts
     * @return
     */
    @Override
    public int insertBatch(List<TsDistrict> tsDistricts){
        return tsDistrictMapper.insertBatch(tsDistricts);
    }

    @Override
    public int updateByPrimaryKey(TsDistrict tsDistrict) throws ServiceException {
        if (StringUtils.isNotEmpty(tsDistrict.getDistId())){
            return tsDistrictMapper.updateByPrimaryKey(tsDistrict);
        }
//        throw new ServiceException(TsDistrictServiceImpl.class.toString()+"出现异常，异常编号"+001);
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TsDistrict tsDistrict) throws ServiceException {
        if (StringUtils.isNotEmpty(tsDistrict.getDistId())){
            return tsDistrictMapper.updateByPrimaryKeySelective(tsDistrict);
        }
//        throw new ServiceException(TsDistrictServiceImpl.class.toString()+"出现异常，异常编号"+002);
        return 0;
    }

    @Override
    public int updateBatch(List<TsDistrict> tsDistricts){
        return tsDistrictMapper.updateBatch(tsDistricts);
    }


    @Override
    public int deleteByPrimaryKey(Object object){
        return  tsDistrictMapper.deleteByPrimaryKey(object);
    }

    @Override
    public int deleteBatchByIds(List<TsDistrict> tsDistricts){
        return  tsDistrictMapper.deleteBatchByIds(tsDistricts);
    }

    @Override
    public List<TsDistrict> selectByCondition(Object object){
        return tsDistrictMapper.selectByExample(object);
    }

    @Override
    public int saveBatch(List<TsDistrict> tsDistricts) {
        List<TsDistrict> tsDistrictsInert = new ArrayList<TsDistrict>();
        List<TsDistrict> tsDistrictsUpdate = new ArrayList<TsDistrict>();
        for (TsDistrict tsDistrict: tsDistricts) {
            /**
            * 没有主键是新增
            */
            if (StringUtils.isEmpty(tsDistrict.getDistId())){
//                tsDistrict.setDistId(IdWorkerGen.nextID());
                tsDistrictsInert.add(tsDistrict);
            }else{
                tsDistrictsUpdate.add(tsDistrict);
            }
        }
        int i = tsDistrictMapper.insertBatch(tsDistrictsInert);
        i = i + tsDistrictMapper.updateBatch(tsDistrictsUpdate);
        return i;
    }

    @Override
    public List<TsDistrict> selectByExample(Object object) {
        return tsDistrictMapper.selectByExample(object);
    }
}
