package com.ranran.system.operate.service.impl;


import com.ranran.system.mapper.TsResourceMapper;
import com.ranran.system.model.TsResource;
import com.ranran.system.operate.service.TsResourceService;
import com.ranran.system.operate.vo.TsResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengrui on 2017/7/17.
 */
@Service
public class TsResourceServiceImpl implements TsResourceService {

    @Autowired
    private TsResourceMapper tsResourceMapper;

    @Override
    public List select(TsResource tsResource) {
        return tsResourceMapper.select(tsResource);
    }

    @Override
    public int updateResData(TsResourceVo tsResourceVo) {
        TsResource tsResource = tsResourceVo.getTsResource();
        List<TsResource> resBtnList = tsResourceVo.getResBtnList();
        int i = 0;
        String resParentNo = "";
        //新增
        if (tsResource.getResId() == null){
            i += tsResourceMapper.insert(tsResource);
        //更新
        }else{
            resParentNo = tsResource.getResNo();
            i += tsResourceMapper.updateByPrimaryKey(tsResource);
        }
        //更新按钮集合
        List<TsResource> updateBtnList = new ArrayList<TsResource>();
        //插入按钮集合
        List<TsResource> insertBtnList = new ArrayList<TsResource>();
        for (TsResource resBtn:resBtnList) {
            if(null == resBtn.getResId()){
                resBtn.setResParentNo(resParentNo);
                insertBtnList.add(resBtn);
            }else{
                updateBtnList.add(resBtn);
            }
        }
        if (insertBtnList.size()>0){
            i += tsResourceMapper.insertBatch(insertBtnList);
        }
        if (updateBtnList.size()>0){
            i += tsResourceMapper.updateBatch(updateBtnList);
        }
        return i;
    }
}

