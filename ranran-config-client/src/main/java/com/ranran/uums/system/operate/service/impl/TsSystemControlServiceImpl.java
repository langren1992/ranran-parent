package com.ranran.uums.system.operate.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.BaseModel;
import com.ranran.core.ResponseResult;
import com.ranran.uums.system.mapper.TsSystemControlMapper;
import com.ranran.uums.system.model.TsSystemControl;
import com.ranran.uums.system.operate.service.TsSystemControlService;
import com.ranran.uums.system.operate.vo.*;
import org.apache.commons.lang.StringUtils;
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
    public PageInfo selectTsSystemControl(TsSystemControlSelectVo tsSystemControlSearchVo) {
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage(tsSystemControlSearchVo.getPage(),tsSystemControlSearchVo.getRows());
        Example example = new Example(TsSystemControl.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tsSystemControlSearchVo.getTscKey())){
            criteria.andEqualTo("tscKey",tsSystemControlSearchVo.getTscKey());
        }
        if(StringUtils.isNotBlank(tsSystemControlSearchVo.getTscValue())){
            criteria.andEqualTo("tscValue",tsSystemControlSearchVo.getTscValue());
        }
        if (StringUtils.isNotBlank(tsSystemControlSearchVo.getSort())){
            if(BaseModel.OrderEnum.ASC.name.equalsIgnoreCase(tsSystemControlSearchVo.getOrder())){
                example.orderBy(tsSystemControlSearchVo.getSort()).asc();
            }
            if(BaseModel.OrderEnum.DESC.name.equalsIgnoreCase(tsSystemControlSearchVo.getOrder())){
                example.orderBy(tsSystemControlSearchVo.getSort()).desc();
            }

        }
        example.orderBy("modifyTime").desc();
        List<TsSystemControl> tsRoles = tsSystemControlMapper.selectByExample(example);
        pageInfo.setList(tsRoles);
        pageInfo.setTotal(((Page<TsSystemControl>) tsRoles).getTotal());
        return pageInfo;
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

    /**
     * 删除（物理删除）系统控制参数
     *
     * @param tsSystemControlDeleteVos 删除数据视图
     * @return 返回操作结果
     */
    @Override
    public int deleteTsSystemControls(List<TsSystemControlDeleteVo> tsSystemControlDeleteVos) {
        List<TsSystemControl> systemControls = new ArrayList<TsSystemControl>();
        TsSystemControl systemControl;
        for (int i = 0,size = tsSystemControlDeleteVos.size(); i < size; i++) {
            systemControl = new TsSystemControl();
            BeanUtils.copyProperties(tsSystemControlDeleteVos.get(i),systemControl);
            systemControls.add(systemControl);

        }
        return tsSystemControlMapper.deleteBatchByIds(systemControls);
    }

    /**
     * 导入系统控制参数
     *
     * @param tsSystemControlImportVos 导入数据视图
     * @return 导入结果
     */
    @Override
    public ResponseResult importTsSystemControls(List<TsSystemControlImportVo> tsSystemControlImportVos) {
        ResponseResult responseResult = new ResponseResult();
        List<TsSystemControl> systemControls = new ArrayList<TsSystemControl>();
        TsSystemControl systemControl;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0,size = tsSystemControlImportVos.size(); i < size; i++) {
            systemControl = new TsSystemControl();
            BeanUtils.copyProperties(tsSystemControlImportVos.get(i),systemControl);
            if(tsSystemControlMapper.selectCount(systemControl)==0){
                systemControls.add(systemControl);
            }else {
                stringBuffer.append("第"+(i+2)+"行控制键:【"+systemControl.getTscKey()+"】的数据未导入成功；<br / >");
            }
        }
        int i = 0;
        if (systemControls.size()>0){
            i += tsSystemControlMapper.insertBatch(systemControls);
        }
        responseResult.success = true;
        responseResult.message = stringBuffer.append("成功导入"+i+"条数据！").toString();
        return responseResult;
    }

    /**
     * 导出系统控制参数
     *
     * @param tsSystemControlSelectVo 根据查询条件查询导出结果
     * @return 导出结果
     */
    @Override
    public List<TsSystemControlExportVo> exportTsSystemControls(TsSystemControlSelectVo tsSystemControlSelectVo) {
        Example example = new Example(TsSystemControl.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tsSystemControlSelectVo.getTscKey())){
            criteria.andEqualTo("tscKey",tsSystemControlSelectVo.getTscKey());
        }
        if(StringUtils.isNotBlank(tsSystemControlSelectVo.getTscValue())){
            criteria.andEqualTo("tscValue",tsSystemControlSelectVo.getTscValue());
        }
        List<TsSystemControl> systemControls = tsSystemControlMapper.selectByExample(example);
        List<TsSystemControlExportVo> tsSystemControlExportVos = new ArrayList<TsSystemControlExportVo>();
        TsSystemControlExportVo tsSystemControlExportVo;
        for (int i = 0,size = systemControls.size(); i < size; i++) {
            tsSystemControlExportVo = new TsSystemControlExportVo();
            BeanUtils.copyProperties(systemControls.get(i),tsSystemControlExportVo);
            tsSystemControlExportVos.add(tsSystemControlExportVo);
        }
        return tsSystemControlExportVos;
    }


}
