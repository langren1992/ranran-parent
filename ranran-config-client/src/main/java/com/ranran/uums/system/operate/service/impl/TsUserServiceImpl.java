package com.ranran.uums.system.operate.service.impl;

import com.ranran.core.exception.ServiceException;

import com.ranran.core.shiro.util.StringUtils;
import com.ranran.uums.system.mapper.TsUserMapper;
import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* Created by zengrui on 2017-07-25 21:46:14.
*/
@Service
public class TsUserServiceImpl implements TsUserService {

    @Autowired
    private TsUserMapper tsUserMapper;

    /**
     * @descripte
     * @param tsUser
     * @return TsUser
     */
    @Override
    public TsUser selectOne(TsUser tsUser){
        return tsUserMapper.selectOne(tsUser);
    }

    @Override
    public List<TsUser> select(TsUser tsUser){
        return tsUserMapper.select(tsUser);
    }

    /**
     * 插入空
     * */
    @Override
    public int insert(TsUser tsUser){
        return tsUserMapper.insert(tsUser);
    }

    /**
     * 插入不为空的
     * */
    @Override
        public int insertSelective(TsUser tsUser){
        return tsUserMapper.insertSelective(tsUser);
    }

    /**
     *
     * @param tsUsers
     * @return
     */
    @Override
    public int insertBatch(List<TsUser> tsUsers){
        return tsUserMapper.insertBatch(tsUsers);
    }

    @Override
    public int updateByPrimaryKey(TsUser tsUser) throws ServiceException {
        if (StringUtils.isNotEmpty(tsUser.getUserId())){
            return tsUserMapper.updateByPrimaryKey(tsUser);
        }
        throw new ServiceException(TsUserServiceImpl.class.toString()+"出现异常，异常编号"+001);
    }

    @Override
    public int updateByPrimaryKeySelective(TsUser tsUser) throws ServiceException {
        if (StringUtils.isNotEmpty(tsUser.getUserId())){
            return tsUserMapper.updateByPrimaryKeySelective(tsUser);
        }
        throw new ServiceException(TsUserServiceImpl.class.toString()+"出现异常，异常编号"+002);
    }

    @Override
    public int updateBatch(List<TsUser> tsUsers){
        return tsUserMapper.updateBatch(tsUsers);
    }


    @Override
    public int deleteByPrimaryKey(Object object){
        return  tsUserMapper.deleteByPrimaryKey(object);
    }

    @Override
    public int deleteBatchByIds(List<TsUser> tsUsers){
        return  tsUserMapper.deleteBatchByIds(tsUsers);
    }

    @Override
    public List<TsUser> selectByCondition(Object object){
        return tsUserMapper.selectByExample(object);
    }

    @Override
    public int saveBatch(List<TsUser> tsUsers) {
        List<TsUser> tsUsersInert = new ArrayList<TsUser>();
        List<TsUser> tsUsersUpdate = new ArrayList<TsUser>();
        for (TsUser tsUser: tsUsers) {
            /**
            * 没有主键是新增
            */
            if (StringUtils.isEmpty(tsUser.getUserId())){
//                tsUser.setUserId(IdWorkerGen.nextID());
                tsUsersInert.add(tsUser);
            }else{
                tsUsersUpdate.add(tsUser);
            }
        }
        int i = tsUserMapper.insertBatch(tsUsersInert);
        i = i + tsUserMapper.updateBatch(tsUsersUpdate);
        return i;
    }
}
