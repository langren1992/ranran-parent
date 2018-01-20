package com.ranran.uums.system.operate.service.impl;


import com.ranran.uums.system.mapper.TsDeptMapper;
import com.ranran.uums.system.model.TsDept;
import com.ranran.uums.system.operate.service.TsDeptService;
import com.ranran.uums.system.operate.vo.TsDeptSearchVo;
import com.ranran.uums.system.operate.vo.TsDeptUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
     * 查询部门信息，生成树形菜单
     *
     * @param tsDeptSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    @Override
    public List<TsDept> selectDept(TsDeptSearchVo tsDeptSearchVo) {
        // TODO: 2018/1/20  曾睿 添加部门数创建数据生成
        Example example = new Example(TsDept.class);
        return tsDeptMapper.selectByExample(example);
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param tsDeptUpdateVos 操作数据视图
     * @return 返回操作成功数量
     */
    @Override
    public int updateDepts(List<TsDeptUpdateVo> tsDeptUpdateVos) {
        // TODO: 2018/1/20 曾睿 部门新增、启用、停用、删除
        // 新增列表
        List<TsDept> insertList = new ArrayList<TsDept>();
        // 更新列表
        List<TsDept> updateList = new ArrayList<TsDept>();
        TsDept tsDept;
        for (int i = 0,size = tsDeptUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsDeptUpdateVos.get(i).getDeptId() == null){
                tsDept = new TsDept();
                BeanUtils.copyProperties(tsDeptUpdateVos.get(i),tsDept);
                insertList.add(tsDept);
            } else {
                tsDept = new TsDept();
                BeanUtils.copyProperties(tsDeptUpdateVos.get(i),tsDept);
                updateList.add(tsDept);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i += tsDeptMapper.insertBatch(insertList);
        }
        if (updateList.size()>0){
            i += tsDeptMapper.updateBatch(updateList);
        }
        return i;
    }
}
