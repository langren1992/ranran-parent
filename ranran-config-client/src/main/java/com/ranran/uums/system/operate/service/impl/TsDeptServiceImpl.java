package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.exception.ServiceException;
import com.ranran.core.shiro.util.StringUtils;
import com.ranran.uums.system.mapper.TsDeptMapper;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsDeptService;
import com.ranran.uums.system.operate.vo.TsCompanyDeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* Created by zengrui on 2017-08-11 12:10:02.
*/
@Service
public class TsDeptServiceImpl implements TsDeptService {

    @Autowired
    private TsDeptMapper tsDeptMapper;


    /**
     * @descripte
     * @param tsDept
     * @return TsDept
     */
    @Override
    public TsDept selectOne(TsDept tsDept){
        return tsDeptMapper.selectOne(tsDept);
    }

    @Override
    public List<TsDept> select(TsDept tsDept){
        return tsDeptMapper.select(tsDept);
    }

    /**
     * 插入空
     * */
    @Override
    public int insert(TsDept tsDept){
        return tsDeptMapper.insert(tsDept);
    }

    /**
     * 插入不为空的
     * */
    @Override
        public int insertSelective(TsDept tsDept){
        return tsDeptMapper.insertSelective(tsDept);
    }

    /**
     *
     * @param tsDepts
     * @return
     */
    @Override
    public int insertBatch(List<TsDept> tsDepts){
        return tsDeptMapper.insertBatch(tsDepts);
    }

    @Override
    public int updateByPrimaryKey(TsDept tsDept) throws ServiceException {
        if (StringUtils.isNotEmpty(tsDept.getDeptId())){
            return tsDeptMapper.updateByPrimaryKey(tsDept);
        }
//        throw new ServiceException(TsDeptServiceImpl.class.toString()+"出现异常，异常编号"+001);
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TsDept tsDept) throws ServiceException {
        if (StringUtils.isNotEmpty(tsDept.getDeptId())){
            return tsDeptMapper.updateByPrimaryKeySelective(tsDept);
        }
//        throw new ServiceException(TsDeptServiceImpl.class.toString()+"出现异常，异常编号"+002);
        return 0;
    }

    @Override
    public int updateBatch(List<TsDept> tsDepts){
        return tsDeptMapper.updateBatch(tsDepts);
    }


    @Override
    public int deleteByPrimaryKey(Object object){
        return  tsDeptMapper.deleteByPrimaryKey(object);
    }

    @Override
    public int deleteBatchByIds(List<TsDept> tsDepts){
        return  tsDeptMapper.deleteBatchByIds(tsDepts);
    }

    @Override
    public List<TsDept> selectByCondition(Object object){
        return tsDeptMapper.selectByExample(object);
    }

    @Override
    public int saveBatch(List<TsDept> tsDepts) {
        List<TsDept> tsDeptsInert = new ArrayList<TsDept>();
        List<TsDept> tsDeptsUpdate = new ArrayList<TsDept>();
        for (TsDept tsDept: tsDepts) {
            /**
            * 没有主键是新增
            */
            if (StringUtils.isEmpty(tsDept.getDeptId())){
//                tsDept.setDeptId(IdWorkerGen.nextID());
                tsDeptsInert.add(tsDept);
            }else{
                tsDeptsUpdate.add(tsDept);
            }
        }
        int i = 0;
        if (tsDeptsInert.size() >= 1){
            i = tsDeptMapper.insertBatch(tsDeptsInert);
        }
        if (tsDeptsUpdate.size() >=1){
            i = i + tsDeptMapper.updateBatch(tsDeptsUpdate);
        }
        return i;
    }

    @Override
    public TsCompanyDeptVo loadCompanyDept(String userNo) {
        return tsDeptMapper.loadCompanyDept(userNo);
    }
}
