package com.ranran.system.operate.service;

import com.ranran.core.ResponseResult;
import com.ranran.system.operate.vo.DroplistVo;

/**
 * @Title DroplistController
 * @description 下拉列表查询控制列表
 * @author zengrui
 * @createTime 2018/4/6 21:36
 * @modifyTime 2018/4/6 21:36
 **/
public interface DroplistService {

    /**
     * @Title queryDropListData
     * @description 查询下拉列表数据
     * @param droplistVo DroplistVo
     * @author zengrui
     * @createTime 2018/4/6 21:36
     * @modifyTime 2018/4/6 21:36
     * @return ResponseResult
     **/
    public ResponseResult queryDropListData(DroplistVo droplistVo);
}
