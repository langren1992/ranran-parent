package com.ranran.uums.system.operate.service;


import com.ranran.uums.system.model.TsUser;
import com.ranran.uums.system.operate.vo.TsUserSearchVo;
import com.ranran.uums.system.operate.vo.TsUserUpdateVo;

import java.util.List;

/**
 * 用户服务
 * Created by zengrui on 2017-08-11 12:10:02.
 */
public interface TsUserService {

    /**
     * 查询部门信息，生成树形菜单
     * @param tsUserSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    List<TsUser> selectUser(TsUserSearchVo tsUserSearchVo);

    /**
     * 新增、启用、停用、删除（逻辑阐述）用户
     * @param tsUserUpdateVos 操作数据视图
     * @return 返回操作成功数量
     */
    int updateUsers(List<TsUserUpdateVo> tsUserUpdateVos);

    /**
     * 初始化密码
     *
     * @param tsUserUpdateVo 初始化视图类
     * @return 返回操作成功数量
     */
    int updateUserPwd(TsUserUpdateVo tsUserUpdateVo);
}
