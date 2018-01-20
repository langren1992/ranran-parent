package com.ranran.uums.system.operate.service.impl;


import com.ranran.core.shiro.PasswordHelper;
import com.ranran.uums.system.mapper.TsUserMapper;
import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.service.TsUserService;
import com.ranran.uums.system.operate.vo.TsUserSearchVo;
import com.ranran.uums.system.operate.vo.TsUserUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息逻辑类
 *
 * Created by zengrui on 2017-08-11 12:10:02.
 */
@Service
public class TsUserServiceImpl implements TsUserService {

    @Autowired
    private TsUserMapper tsUserMapper;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param tsUserSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    @Override
    public List<TsUser> selectUser(TsUserSearchVo tsUserSearchVo) {
        // TODO: 2018/1/20  曾睿 添加部门数创建数据生成
        Example example = new Example(TsUser.class);
        example.createCriteria().andEqualTo("userDeptNo",tsUserSearchVo.getUserDeptNo());
        return tsUserMapper.selectByExample(example);
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param tsUserUpdateVos 操作数据视图
     * @return 返回操作成功数量
     */
    @Override
    public int updateUsers(List<TsUserUpdateVo> tsUserUpdateVos) {
        // TODO: 2018/1/20 曾睿 用户新增、启用、停用、删除
        // 新增列表
        List<TsUser> insertList = new ArrayList<TsUser>();
        // 更新列表
        List<TsUser> updateList = new ArrayList<TsUser>();
        TsUser tsUser;
        for (int i = 0,size = tsUserUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsUserUpdateVos.get(i).getUserId() == null){
                tsUser = new TsUser();
                BeanUtils.copyProperties(tsUserUpdateVos.get(i),tsUser);
                //默认密码
                tsUser.setUserPassword("123456");
                //默认在职
                tsUser.setUserStatus(1);
                //密码加密
                passwordHelper.encryptPassword(tsUser);
                insertList.add(tsUser);
            } else {
                tsUser = new TsUser();
                BeanUtils.copyProperties(tsUserUpdateVos.get(i),tsUser);
                updateList.add(tsUser);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i += tsUserMapper.insertBatch(insertList);
        }
        if (updateList.size()>0){
            i += tsUserMapper.updateBatch(updateList);
        }
        return i;
    }

    /**
     * 初始化密码
     *
     * @param tsUserUpdateVo 初始化视图类
     * @return 返回操作成功数量
     */
    @Override
    public int updateUserPwd(TsUserUpdateVo tsUserUpdateVo) {
        // TODO: 2018/1/20 曾睿 初始化密码
        TsUser tsUser = new TsUser();
        BeanUtils.copyProperties(tsUserUpdateVo,tsUser);
        //默认密码
        tsUser.setUserPassword("123456");
        //密码加密
        passwordHelper.encryptPassword(tsUser);
        return this.tsUserMapper.updateByPrimaryKey(tsUser);
    }
}
