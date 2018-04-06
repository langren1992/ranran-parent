package com.ranran.system.operate.service;

import com.github.pagehelper.PageInfo;
import com.ranran.core.ResponseResult;
import com.ranran.system.operate.vo.*;
import com.ranran.system.operate.vo.*;

import java.util.List;

/*
 * 系统控制参数逻辑规范
 * @creator zengrui 2018-01-30 10:13
 */
public interface TsSystemControlService {

    /**
    * 查询系统控制参数信息
    * @param tsSystemControlSelectVo 查询条件视图
    * @return 返回部门翻页数据
    */
    PageInfo selectTsSystemControl(TsSystemControlSelectVo tsSystemControlSelectVo);

    /**
    * 新增、启用、停用、删除（逻辑阐述）系统控制参数
    * @param tsSystemControlSelectVo 操作数据视图
    * @return 返回操作成功数量
    */
    int updateTsSystemControls(List<TsSystemControlUpdateVo> tsSystemControlSelectVo);

    /**
     * 删除（物理删除）系统控制参数
     *
     * @param tsSystemControlDeleteVos 删除数据视图
     * @return 返回操作结果
     */
    int deleteTsSystemControls(List<TsSystemControlDeleteVo> tsSystemControlDeleteVos);

    /**
     * 导入系统控制参数
     *
     * @param tsSystemControlImportVos 导入数据视图
     * @return 导入结果
     */
    ResponseResult importTsSystemControls(List<TsSystemControlImportVo> tsSystemControlImportVos);
    /**
     * 导出系统控制参数
     *
     * @param tsSystemControlSelectVo 根据查询条件查询导出结果
     * @return 导出结果
     */
    List<TsSystemControlExportVo> exportTsSystemControls(TsSystemControlSelectVo tsSystemControlSelectVo);
}
