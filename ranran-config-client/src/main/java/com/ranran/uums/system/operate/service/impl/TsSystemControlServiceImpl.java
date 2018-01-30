package com.ranran.uums.system.operate.service.impl;


import com.ranran.uums.system.mapper.TsSystemControlMapper;
import com.ranran.uums.system.model.TsSystemControl;
import com.ranran.uums.system.operate.service.TsSystemControlService;
import com.ranran.uums.system.operate.vo.TsSystemControlSelectVo;
import com.ranran.uums.system.operate.vo.TsSystemControlUpdateVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统控制参数实施
 * @auth 曾睿 on 2018-01-30 22:13:08.
 */
@Service
public class TsSystemControlServiceImpl implements TsSystemControlService {

    @Autowired
    private TsSystemControlMapper tsSystemControlMapper;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param tsSystemControlSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    @Override
    public List<TsSystemControl> selectTsSystemControl(TsSystemControlSelectVo tsSystemControlSearchVo) {
        Example example = new Example(TsSystemControl.class);
        return tsSystemControlMapper.selectByExample(example);
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）部门
    *
    * @param tsSystemControlUpdateVos 操作数据视图
    * @return 返回操作成功数量
    */
    @Override
    public int updateTsSystemControls(List<TsSystemControlUpdateVo> tsSystemControlUpdateVos) {
        // 新增列表
        List<TsSystemControl> insertList = new ArrayList<TsSystemControl>();
        // 更新列表
        List<TsSystemControl> updateList = new ArrayList<TsSystemControl>();
        TsSystemControl tsSystemControl;
        for (int i = 0,size = tsSystemControlUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsSystemControlUpdateVos.get(i).getTscId() == null){
                tsSystemControl = new TsSystemControl();
                BeanUtils.copyProperties(tsSystemControlUpdateVos.get(i),tsSystemControl);
                insertList.add(tsSystemControl);
            } else {
                tsSystemControl = new TsSystemControl();
                BeanUtils.copyProperties(tsSystemControlUpdateVos.get(i),tsSystemControl);
                updateList.add(tsSystemControl);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i += tsSystemControlMapper.insertBatch(insertList);
        }
        if (updateList.size()>0){
            i += tsSystemControlMapper.updateBatch(updateList);
        }
        return i;
    }
}
