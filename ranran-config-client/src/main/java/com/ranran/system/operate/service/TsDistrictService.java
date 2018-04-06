package com.ranran.system.operate.service;

import com.github.pagehelper.PageInfo;
import com.ranran.core.ResponseResult;
import com.ranran.system.operate.vo.*;
import com.ranran.system.operate.vo.*;

import java.util.List;

/*
 * 逻辑
 * @creator zengrui 2018-02-03 05:27
 */
public interface TsDistrictService {

    /**
     * 查询信息
     * @param tsDistrictSelectVo 查询条件视图
     * @return 返回部门翻页数据
     */
    PageInfo selectTsDistrict(TsDistrictSelectVo tsDistrictSelectVo);

    /**
     * 新增、启用、停用、删除（逻辑阐述）
     * @param tsDistrictSelectVo 操作数据视图
     * @return 返回操作成功数量
     */
    int updateTsDistricts(List<TsDistrictUpdateVo> tsDistrictSelectVo);

    /**
     * 删除（物理删除）
     *
     * @param tsDistrictDeleteVos 删除数据视图
     * @return 返回操作结果
     */
    int deleteTsDistricts(List<TsDistrictDeleteVo> tsDistrictDeleteVos);

    /**
     * 导入
     *
     * @param tsDistrictImportVos 导入数据视图
     * @return 导入结果
     */
    ResponseResult importTsDistricts(List<TsDistrictImportVo> tsDistrictImportVos);

    /**
     * 导出
     *
     * @param tsDistrictSelectVo 根据查询条件查询导出结果
     * @return 导出结果
     */
    List<TsDistrictExportVo> exportTsDistricts(TsDistrictSelectVo tsDistrictSelectVo);

    /**
     * 通过第三方获取省市区县信息 高德（IMAP）
     *
     * @param tsDistrictSyncMapVo 请求参数
     * @return 导入成功
     */
    int syncMapTsDistrict(TsDistrictSyncMapVo tsDistrictSyncMapVo);

    /**
     * 省市区县级联查询
     *
     * @param tsDistrictProvCityDistVo 请求参数
     * @return 返回操作结果
     */
    List getProvCityDist(TsDistrictProvCityDistVo tsDistrictProvCityDistVo);
}
