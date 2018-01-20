package com.ranran.uums.system.operate.service;


import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.vo.TsDeptSearchVo;
import com.ranran.uums.system.operate.vo.TsDeptUpdateVo;

import java.util.List;

/**
 * 部门服务
 * Created by zengrui on 2017-08-11 12:10:02.
 */
public interface TsDeptService {

    /**
     * 查询部门信息，生成树形菜单
     * @param tsDeptSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    List<TsDept> selectDept(TsDeptSearchVo tsDeptSearchVo);

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     * @param tsDeptUpdateVos 操作数据视图
     * @return 返回操作成功数量
     */
    int updateDepts(List<TsDeptUpdateVo> tsDeptUpdateVos);
}
