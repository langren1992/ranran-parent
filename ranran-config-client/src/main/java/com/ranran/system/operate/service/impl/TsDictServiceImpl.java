package com.ranran.system.operate.service.impl;


import com.ranran.core.redis.RanranRedisTemplate;
import com.ranran.system.mapper.TsDictMapper;
import com.ranran.system.model.TsDict;
import com.ranran.system.operate.service.TsDictService;
import com.ranran.system.mapper.TsResourceMapper;
import com.ranran.system.model.TsResource;
import com.ranran.system.operate.vo.TsDictSearchVo;
import com.ranran.system.operate.vo.TsDictTsResourceSearchVo;
import com.ranran.system.operate.vo.TsDictUpdateVo;
import com.ranran.system.operate.redis.DictKeyRedis;
import com.ranran.system.operate.redis.DictValueRedis;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 数据字典实施
 * @auth 曾睿 on 2018-01-21 11:14:51.
 */
@Service
public class TsDictServiceImpl implements TsDictService {

    @Autowired
    private TsDictMapper tsDictMapper;

    @Autowired
    private TsResourceMapper tsResourceMapper;

    @Autowired
    private RanranRedisTemplate ranranRedisTemplate;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param tsDictSearchVo 查询条件视图
     * @return 返回部门列表 响应结果
     */
    @Override
    public List<TsDict> selectTsDict(TsDictSearchVo tsDictSearchVo) {
        // TODO: 2018-01-21 11:14:51  曾睿 添加部门数创建数据生成
        Example example = new Example(TsDict.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(tsDictSearchVo.getTdResCode())){
            criteria.andEqualTo("tdResCode",tsDictSearchVo.getTdResCode());
        }
        if (StringUtils.isNotEmpty(tsDictSearchVo.getTdType())){
            criteria.andEqualTo("tdType",tsDictSearchVo.getTdType());
        }
        if (StringUtils.isNotEmpty(tsDictSearchVo.getTdKey())){
            criteria.andEqualTo("tdKey",tsDictSearchVo.getTdKey());
        }
        return tsDictMapper.selectByExample(example);
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）部门
    *
    * @param tsDictUpdateVos 操作数据视图
    * @return 返回操作成功数量
    */
    @Override
    public int updateTsDicts(List<TsDictUpdateVo> tsDictUpdateVos){
        // TODO: 2018-01-21 11:14:51 曾睿 部门新增、启用、停用、删除
        // 新增列表
        List<TsDict> insertList = new ArrayList<TsDict>();
        // 更新列表
        List<TsDict> updateList = new ArrayList<TsDict>();
        TsDict tsDict;
        for (int i = 0,size = tsDictUpdateVos.size(); i < size; i++) {
            // 判断新增、更新
            if (tsDictUpdateVos.get(i).getTdId() == null){
                tsDict = new TsDict();
                BeanUtils.copyProperties(tsDictUpdateVos.get(i),tsDict);
                insertList.add(tsDict);
            } else {
                tsDict = new TsDict();
                BeanUtils.copyProperties(tsDictUpdateVos.get(i),tsDict);
                updateList.add(tsDict);
            }
        }
        int i = 0;
        if (insertList.size()>0){
            i += tsDictMapper.insertBatch(insertList);
            // 添加缓存
            addCache(insertList);
        }
        if (updateList.size()>0){
            i += tsDictMapper.updateBatch(updateList);
            // 更新缓存
            updateCache(updateList);
        }
        return i;
    }

    /**
     * 更新缓存
     * @param updateList
     */
    private void updateCache(List<TsDict> updateList) {
        // 寻找数据字段key
        Set<String> stringSet = new HashSet<String>();
        for (TsDict tsDict:updateList) {
            if (StringUtils.isEmpty(tsDict.getTdParentCode())){
                stringSet.add(tsDict.getTdKey());
            }
        }
        // 查询所有key数据
        Example example = new Example(TsDict.class);
        example.createCriteria().andIn("tdKey",stringSet);
        List<TsDict> tsDicts = tsDictMapper.selectByExample(example);
        // 生成缓存对象
        Map<String,DictKeyRedis> dictKeyRedisMap = new HashMap<String, DictKeyRedis>();
        optDictKeyRedis(tsDicts,dictKeyRedisMap);
        // 操作缓存先删除后新增
        DictKeyRedis dictKeyRedis;
        DictKeyRedis dictKeyRedisTmp;
        for (String key: dictKeyRedisMap.keySet()) {
            dictKeyRedis = dictKeyRedisMap.get(key);
            dictKeyRedisTmp = new DictKeyRedis();
            dictKeyRedisTmp.setDictValues(dictKeyRedis.getDictValues());
            dictKeyRedisTmp.setTsKey(dictKeyRedis.getTsKey());
            if (ranranRedisTemplate.hasKey(dictKeyRedis)){
                ranranRedisTemplate.delete(dictKeyRedis);
            }
            ranranRedisTemplate.setForString(dictKeyRedisTmp);
        }
    }



    /**
     * 添加缓存
     * @param insertList
     */
    private void addCache(List<TsDict> insertList){
        // 寻找数据字段key
        Map<String,DictKeyRedis> dictKeyRedisMap = new HashMap<String, DictKeyRedis>();
        optDictKeyRedis(insertList,dictKeyRedisMap);
        // 操作缓存先删除
        for (String key: dictKeyRedisMap.keySet()) {
            ranranRedisTemplate.setForString(dictKeyRedisMap.get(key));
        }
    }

    /**
     * 生成缓存对象
     * @param tsDicts
     */
    private void optDictKeyRedis(List<TsDict> tsDicts,Map<String,DictKeyRedis> dictKeyRedisMap) {
        DictKeyRedis dictKeyRedis;
        for (TsDict tsDict:tsDicts) {
            if (StringUtils.isEmpty(tsDict.getTdParentCode())){
                dictKeyRedis = new DictKeyRedis();
                dictKeyRedis.setTsKey(tsDict.getTdKey());
                dictKeyRedisMap.put(tsDict.getTdKey(),dictKeyRedis);
            }
        }
        List<DictValueRedis> dictValueRedisList = new ArrayList<DictValueRedis>();
        DictValueRedis dictValueRedis;
        // 根据key字段组装value
        for (TsDict tsDict:tsDicts){
            // 父节点不为空且包含在Map中为value
            if (StringUtils.isNotEmpty(tsDict.getTdParentCode()) && dictKeyRedisMap.containsKey(tsDict.getTdKey())){
                DictKeyRedis dictKeyRedisTmp = dictKeyRedisMap.get(tsDict.getTdKey());
                dictValueRedis = new DictValueRedis();
                dictValueRedis.setTsName(tsDict.getTdName());
                dictValueRedis.setTsCode(tsDict.getTdCode());
                if (null == dictKeyRedisTmp.getDictValues() || dictKeyRedisTmp.getDictValues().size()== 0){
                    dictValueRedisList.add(dictValueRedis);
                    dictKeyRedisMap.get(tsDict.getTdKey()).setDictValues(dictValueRedisList);
                }else {
                    dictKeyRedisMap.get(tsDict.getTdKey()).getDictValues().add(dictValueRedis);
                }
            }
        }
    }

    /**
     * 数据字典资源信息
     *
     * @param tsDictTsResourceSearchVo 条件请求视图
     * @return 资源列表
     */
    @Override
    public List selectTsDictTsResource(TsDictTsResourceSearchVo tsDictTsResourceSearchVo) {
        // TODO: 2018-01-21 11:14:51  曾睿 添加部门数创建数据生成
        Example example = new Example(TsResource.class);
        example.createCriteria().andEqualTo("resType",1);
        return tsResourceMapper.selectByExample(example);
    }
}
