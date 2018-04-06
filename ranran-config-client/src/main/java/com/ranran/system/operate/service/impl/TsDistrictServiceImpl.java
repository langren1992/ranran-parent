package com.ranran.system.operate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranran.core.BaseModel;
import com.ranran.core.HttpRequest;
import com.ranran.core.ResponseResult;
import com.ranran.system.model.TsDistrict;
import com.ranran.system.operate.vo.*;
import com.ranran.system.mapper.TsDistrictMapper;
import com.ranran.system.mapper.TsSystemControlMapper;
import com.ranran.system.model.TsSystemControl;
import com.ranran.system.operate.service.TsDistrictService;
import com.ranran.system.operate.vo.*;
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

    @Autowired
    private TsSystemControlMapper tsSystemControlMapper ;

    /**
    * 查询信息
    * @param tsDistrictSelectVo 查询条件视图
    * @return 返回部门翻页数据
    */
    @Override
    public PageInfo selectTsDistrict(TsDistrictSelectVo tsDistrictSelectVo) {
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
        if(StringUtils.isNotBlank(tsDistrictSelectVo.getDistCode())){
            criteria.andEqualTo("distCode",tsDistrictSelectVo.getDistCode());
        }
        if(StringUtils.isNotBlank(tsDistrictSelectVo.getDistName())){
            criteria.andEqualTo("distName",tsDistrictSelectVo.getDistName());
        }
        if(StringUtils.isNotBlank(tsDistrictSelectVo.getDistParentCode())){
            criteria.andEqualTo("distParentCode",tsDistrictSelectVo.getDistParentCode());
        }
        if(StringUtils.isNotBlank(tsDistrictSelectVo.getDistParentName())){
            criteria.andEqualTo("distParentName",tsDistrictSelectVo.getDistParentName());
        }
        if(StringUtils.isNotBlank(tsDistrictSelectVo.getDistLevel())){
            criteria.andEqualTo("distLevel",tsDistrictSelectVo.getDistLevel());
        }
        example.orderBy("distCode");
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

    /**
     * 通过第三方获取省市区县信息 高德（IMAP）
     *
     * @param tsDistrictSyncMapVo 请求参数
     * @return 导入成功数量
     */
    @Override
    public int syncMapTsDistrict(TsDistrictSyncMapVo tsDistrictSyncMapVo) {
        TsSystemControl tsSystemControl = new TsSystemControl();
        tsSystemControl.setTscKey("MAP_KEY");
        tsSystemControl = tsSystemControlMapper.selectOne(tsSystemControl);
        String mapKey = tsSystemControl.getTscValue();
        tsSystemControl = new TsSystemControl();
        tsSystemControl.setTscKey("DISTRICT_SYNC_URL");
        tsSystemControl = tsSystemControlMapper.selectOne(tsSystemControl);
        String districtSyncUrl = tsSystemControl.getTscValue();
        districtSyncUrl = districtSyncUrl.replace("MAP_KEY",mapKey);
        String sysncTsDistrictJson = HttpRequest.sendGet(districtSyncUrl);
        SyncTsDistrictResultJson syncTsDistrictResult = JSONObject.parseObject(sysncTsDistrictJson,SyncTsDistrictResultJson.class);
        // 国家层级
        List<TsDistrict> tsDistrictCountry =  new ArrayList<TsDistrict>();
        // 省市层级
        List<SyncTsDistrictJson> districtProvince = new ArrayList<SyncTsDistrictJson>();
        List<TsDistrict> tsDistrictProvince =  new ArrayList<TsDistrict>();
        // 市市层级
        List<SyncTsDistrictJson> districtCity = new ArrayList<SyncTsDistrictJson>();
        List<TsDistrict> tsDistrictCity =  new ArrayList<TsDistrict>();
        // 区县层级
        List<TsDistrict> tsDistrict =  new ArrayList<TsDistrict>();
        // 添加城市 和 省
        TsDistrict tsDistrictTmp;
        for (int i = 0,size = syncTsDistrictResult.getDistricts().size(); i < size; i++) {
            tsDistrictTmp = new TsDistrict();
            tsDistrictTmp.setDistCode(syncTsDistrictResult.getDistricts().get(i).getAdcode());
            tsDistrictTmp.setDistName(syncTsDistrictResult.getDistricts().get(i).getName());
            tsDistrictTmp.setDistLevel(syncTsDistrictResult.getDistricts().get(i).getLevel());
            tsDistrictTmp.setDistLonlat(syncTsDistrictResult.getDistricts().get(i).getCenter());
            tsDistrictTmp.setDistCitycode(syncTsDistrictResult.getDistricts().get(i).getCitycode());
            tsDistrictCountry.add(tsDistrictTmp);
            districtProvince.addAll(syncTsDistrictResult.getDistricts().get(i).getDistricts());
            for (int j = 0,tSize = syncTsDistrictResult.getDistricts().get(i).getDistricts().size(); j < tSize; j++) {
                tsDistrictTmp = new TsDistrict();
                tsDistrictTmp.setDistCode(syncTsDistrictResult.getDistricts().get(i).getDistricts().get(j).getAdcode());
                tsDistrictTmp.setDistName(syncTsDistrictResult.getDistricts().get(i).getDistricts().get(j).getName());
                tsDistrictTmp.setDistLevel(syncTsDistrictResult.getDistricts().get(i).getDistricts().get(j).getLevel());
                tsDistrictTmp.setDistLonlat(syncTsDistrictResult.getDistricts().get(i).getDistricts().get(j).getCenter());
                tsDistrictTmp.setDistParentCode(syncTsDistrictResult.getDistricts().get(i).getAdcode());
                tsDistrictTmp.setDistParentName(syncTsDistrictResult.getDistricts().get(i).getName());
                tsDistrictTmp.setDistCitycode(syncTsDistrictResult.getDistricts().get(i).getCitycode());
                tsDistrictProvince.add(tsDistrictTmp);
            }
        }

        for (int i = 0,size = districtProvince.size(); i < size; i++) {
            districtCity.addAll(districtProvince.get(i).getDistricts());
            wapperTsDistrict(districtProvince.get(i),tsDistrictCity);
        }

        for (int i = 0,size = districtCity.size(); i < size; i++) {
            wapperTsDistrict(districtCity.get(i),tsDistrict);
        }
        tsDistrictCountry.addAll(tsDistrictProvince);
        tsDistrictCountry.addAll(tsDistrictCity);
        tsDistrictCountry.addAll(tsDistrict);
        return tsDistrictMapper.insertBatch(tsDistrictCountry);
    }

    /**
     * 省市区县级联查询
     *
     * @param tsDistrictProvCityDistVo 请求参数
     * @return 返回操作结果
     */
    @Override
    public List getProvCityDist(TsDistrictProvCityDistVo tsDistrictProvCityDistVo) {
        Example example = new Example(TsDistrict.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(tsDistrictProvCityDistVo.getDistCode())){
            criteria.andLike("distCode",tsDistrictProvCityDistVo.getDistCode()+"%");
        }
        if(StringUtils.isNotBlank(tsDistrictProvCityDistVo.getDistName())){
            criteria.andLike("distName",tsDistrictProvCityDistVo.getDistName()+"%");
        }
        if(StringUtils.isNotBlank(tsDistrictProvCityDistVo.getDistParentCode())){
            criteria.andEqualTo("distParentCode",tsDistrictProvCityDistVo.getDistParentCode());
        }
        if(StringUtils.isNotBlank(tsDistrictProvCityDistVo.getDistParentName())){
            criteria.andEqualTo("distParentName",tsDistrictProvCityDistVo.getDistParentName());
        }
        if(StringUtils.isNotBlank(tsDistrictProvCityDistVo.getDistLevel())){
            criteria.andEqualTo("distLevel",tsDistrictProvCityDistVo.getDistLevel());
        }
        example.orderBy("distCode");
        return tsDistrictMapper.selectByExample(example);
    }

    /**
     * 组装数据对象
     */
    private void wapperTsDistrict(SyncTsDistrictJson district,List<TsDistrict> tsDistricts){
        TsDistrict tsDistrictTmp;
        for (int j = 0,tSize = district.getDistricts().size(); j < tSize; j++) {
            tsDistrictTmp = new TsDistrict();
            tsDistrictTmp.setDistCode(district.getDistricts().get(j).getAdcode());
            tsDistrictTmp.setDistName(district.getDistricts().get(j).getName());
            tsDistrictTmp.setDistLevel(district.getDistricts().get(j).getLevel());
            tsDistrictTmp.setDistLonlat(district.getDistricts().get(j).getCenter());
            tsDistrictTmp.setDistParentCode(district.getAdcode());
            tsDistrictTmp.setDistParentName(district.getName());
            tsDistrictTmp.setDistCitycode(district.getCitycode());
            tsDistricts.add(tsDistrictTmp);
        }
    }


}
