package com.ranran.uums.system.operate.service;

import com.ranran.uums.system.model.TsDict;
import com.ranran.uums.system.operate.vo.TsDictSearchVo;
import com.ranran.uums.system.operate.vo.TsDictTsResourceSearchVo;
import com.ranran.uums.system.operate.vo.TsDictUpdateVo;

import java.util.List;

/*
 * 数据字典逻辑规范
 * @creator zengrui 2018-01-21 11:14
 */
public interface TsDictService {

    /**
    * 查询数据字典信息
    * @param tsDictSearchVo 查询条件视图
    * @return 返回部门列表 响应结果
    */
    List<TsDict> selectTsDict(TsDictSearchVo tsDictSearchVo);

    /**
    * 新增、启用、停用、删除（逻辑阐述）数据字典
    * @param tsDictUpdateVos 操作数据视图
    * @return 返回操作成功数量
    */
    int updateTsDicts(List<TsDictUpdateVo> tsDictUpdateVos);

    /**
     * 数据字典资源信息
     *
     * @param tsDictTsResourceSearchVo 条件请求视图
     * @return 资源列表
     */
    List selectTsDictTsResource(TsDictTsResourceSearchVo tsDictTsResourceSearchVo);
}
