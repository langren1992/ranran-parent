package com.ranran.uums.system.operate.service;

import com.ranran.uums.system.model.TsSystemControl;
import com.ranran.uums.system.operate.vo.TsSystemControlSelectVo;
import com.ranran.uums.system.operate.vo.TsSystemControlUpdateVo;

import java.util.List;

/*
 * 系统控制参数逻辑规范
 * @creator zengrui 2018-01-30 10:13
 */
public interface TsSystemControlService {

    /**
    * 查询系统控制参数信息
    * @param tsSystemControlSelectVo 查询条件视图
    * @return 返回部门列表 响应结果
    */
    List<TsSystemControl> selectTsSystemControl(TsSystemControlSelectVo tsSystemControlSelectVo);

    /**
    * 新增、启用、停用、删除（逻辑阐述）系统控制参数
    * @param tsSystemControlSelectVo 操作数据视图
    * @return 返回操作成功数量
    */
    int updateTsSystemControls(List<TsSystemControlUpdateVo> tsSystemControlSelectVo);

}
