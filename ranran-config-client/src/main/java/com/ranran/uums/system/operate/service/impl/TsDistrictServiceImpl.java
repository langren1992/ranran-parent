package com.ranran.uums.system.operate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.BaseModel;
import com.ranran.core.ResponseResult;
import com.ranran.uums.system.mapper.TsDistrictMapper;
import com.ranran.uums.system.model.TsDistrict;
import com.ranran.uums.system.operate.service.TsDistrictService;
import com.ranran.uums.system.operate.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
* 逻辑
* @creator zengrui 2018-02-03 05:27
*/
@Service
public class TsDistrictServiceImpl implements TsDistrictService {

    @Autowired
    private TsDistrictMapper  tsDistrictMapper;

    /**
    * 查询信息
    * @param tsDistrictSelectVo 查询条件视图
    * @return 返回部门翻页数据
    */
    @Override
    public PageInfo selectTsDistrict(TsDistrictSelectVo  tsDistrictSelectVo) {
        PageInfo pageInfo = new PageInfo();
        PageHelper.startPage( tsDistrictSelectVo.getPage(), tsDistrictSelectVo.getRows());
        Example example = new Example(TsDistrict.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank( tsDistrictSelectVo.getSort())){
            if(BaseModel.OrderEnum.ASC.name.equalsIgnoreCase( tsDistrictSelectVo.getOrder())){
                example.orderBy(tsDistrictSelectVo.getSort()).asc();
            }
            if(BaseModel.OrderEnum.DESC.name.equalsIgnoreCase( tsDistrictSelectVo.getOrder())){
                example.orderBy(tsDistrictSelectVo.getSort()).desc();
            }
        }
        example.orderBy("modifyTime").desc();
        List<TsDistrict> tsDistricts =  tsDistrictMapper.selectByExample(example);
        pageInfo.setList(tsDistricts);
        pageInfo.setTotal(((Page<TsDistrict>) tsDistricts).getTotal());
        return pageInfo;
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）
    * @param tsDistrictUpdateVos 操作数据视图
    * @return 返回操作成功数量
    */
    @Override
    public int updateTsDistricts(List<TsDistrictUpdateVo>  tsDistrictUpdateVos) {
        // 新增列表
        List<TsDistrict> insertList = new ArrayList<TsDistrict>();
        // 更新列表
        List<TsDistrict> updateList = new ArrayList<TsDistrict>();
        TsDistrict  tsDistrict;
        for (int i = 0,size =  tsDistrictUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsDistrictUpdateVos.get(i).getDistId() == null){
                tsDistrict = new TsDistrict();
                BeanUtils.copyProperties( tsDistrictUpdateVos.get(i), tsDistrict);
                insertList.add( tsDistrict);
            } else {
                tsDistrict = new TsDistrict();
                BeanUtils.copyProperties( tsDistrictUpdateVos.get(i), tsDistrict);
                updateList.add( tsDistrict);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i +=  tsDistrictMapper.insertBatch(insertList);
        }
        if (updateList.size()>0){
            i +=  tsDistrictMapper.updateBatch(updateList);
        }
        return i;
    }

    /**
    * 删除（物理删除）
    *
    * @param tsDistrictDeleteVos 删除数据视图
    * @return 返回操作结果
    */
    @Override
    public int deleteTsDistricts(List<TsDistrictDeleteVo>  tsDistrictDeleteVos) {
        List<TsDistrict> tsDistricts = new ArrayList<TsDistrict>();
        TsDistrict tsDistrict;
        for (int i = 0,size =  tsDistrictDeleteVos.size(); i < size; i++) {
            tsDistrict = new TsDistrict();
            BeanUtils.copyProperties( tsDistrictDeleteVos.get(i),tsDistrict);
            tsDistricts.add(tsDistrict);
        }
        return  tsDistrictMapper.deleteBatchByIds(tsDistricts);
    }

    /**
    * 导入
    *
    * @param tsDistrictImportVos 导入数据视图
    * @return 导入结果
    */
    @Override
    public ResponseResult importTsDistricts(List<TsDistrictImportVo>  tsDistrictImportVos) {
        ResponseResult responseResult = new ResponseResult();
        List<TsDistrict>  tsDistricts = new ArrayList<TsDistrict>();
        TsDistrict tsDistrict;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0,size =  tsDistrictImportVos.size(); i < size; i++) {
            tsDistrict = new TsDistrict();
            BeanUtils.copyProperties( tsDistrictImportVos.get(i),tsDistrict);
            if( tsDistrictMapper.selectCount(tsDistrict)==0){
                tsDistricts.add(tsDistrict);
            }else {
                stringBuffer.append("第"+(i+2)+"行控制键:【"+tsDistrict.getDistCode()+"】的数据未导入成功；<br / >");
            }
        }
        int i = 0;
        if (tsDistricts.size()>0){
            i +=  tsDistrictMapper.insertBatch(tsDistricts);
        }
        responseResult.success = true;
        responseResult.message = stringBuffer.append("成功导入"+i+"条数据！").toString();
        return responseResult;
    }

    /**
    * 导出
    *
    * @param tsDistrictSelectVo 根据查询条件查询导出结果
    * @return 导出结果
    */
    @Override
    public List<TsDistrictExportVo> exportTsDistricts(TsDistrictSelectVo  tsDistrictSelectVo) {
        Example example = new Example(TsDistrict.class);
        Example.Criteria criteria = example.createCriteria();
        List<TsDistrict> systemControls =  tsDistrictMapper.selectByExample(example);
        List<TsDistrictExportVo>  tsDistrictExportVos = new ArrayList<TsDistrictExportVo>();
        TsDistrictExportVo  tsDistrictExportVo;
        for (int i = 0,size = systemControls.size(); i < size; i++) {
            tsDistrictExportVo = new TsDistrictExportVo();
            BeanUtils.copyProperties(systemControls.get(i), tsDistrictExportVo);
            tsDistrictExportVos.add( tsDistrictExportVo);
        }
        return  tsDistrictExportVos;
    }


}
