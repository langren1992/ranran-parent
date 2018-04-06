package com.ranran.system.operate.service.impl;

import com.ranran.core.ResponseResult;
import com.ranran.system.operate.service.DroplistService;
import com.ranran.system.operate.vo.DroplistColumsVo;
import com.ranran.system.operate.vo.DroplistConditionsVo;
import com.ranran.system.operate.vo.DroplistSubTableVo;
import com.ranran.system.operate.vo.DroplistVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DroplistControllerImpl implements DroplistService {

    protected Logger LOGGER = LoggerFactory.getLogger(DroplistControllerImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param droplistVo DroplistVo
     * @return ResponseResult
     * @Title queryDropListData
     * @description 查询下拉列表数据
     * @author zengrui
     * @createTime 2018/4/6 21:36
     * @modifyTime 2018/4/6 21:36
     **/
    @Override
    public ResponseResult queryDropListData(DroplistVo droplistVo) {
        ResponseResult result = new ResponseResult();
        StringBuffer countSql = new StringBuffer();
        countSql.append("SELECT COUNT(1) ");
        String fromSql = wapperFrom(droplistVo);
        countSql.append(fromSql);
        StringBuffer querySql = new StringBuffer();
        querySql.append(wapperSelectColums(droplistVo));
        querySql.append(fromSql);
        if(CollectionUtils.isNotEmpty(droplistVo.getConditions())){
            String whereSql = wapperWhere(droplistVo);
            querySql.append(whereSql);
            countSql.append(whereSql);
        }
        querySql.append(wapperLimit(droplistVo));
        int total = jdbcTemplate.queryForObject(countSql.toString(), Integer.class);
        List<Map<String, Object>> datas =  jdbcTemplate.queryForList(querySql.toString());
        result.total = Long.valueOf(total);
        result.rows = datas.toArray();
        return result;
    }

    private String wapperFrom(DroplistVo param){
        StringBuffer sb = new StringBuffer();
        sb.append(" from ");
        sb.append(param.getMainTable().getTableName()+" ");
        if(StringUtils.isNotEmpty(param.getMainTable().getTableAlias())){
            sb.append(param.getMainTable().getTableAlias()+" ");
        }
        DroplistSubTableVo subTable;
        if (CollectionUtils.isNotEmpty(param.getJoinTable())){
            for (int i = 0,size = param.getJoinTable().size(); i < size; i++) {
                subTable = param.getJoinTable().get(i);
                sb.append(subTable.getJoinType()+" "+subTable.getTableName()+" "+subTable.getTableAlias()+" on "+subTable.getJoinKey());
            }
        }
        return sb.toString();
    }

    private String wapperSelectColums(DroplistVo param){
        StringBuffer sb = new StringBuffer();
        DroplistColumsVo queryColums;
        sb.append(" select ");
        boolean flagFirst = true;
        boolean flagAlias = true;
        for (int i = 0,size = param.getColumns().size(); i < size; i++) {
            queryColums = param.getColumns().get(i);
            if (StringUtils.isNotEmpty(queryColums.getColumnAlias())){
                flagAlias = false;
            }
            if(flagFirst){
                if (!flagAlias){
                    sb.append(queryColums.getColumnAlias()+".");
                }
                flagFirst = false;
            }else{
                sb.append(", ");
                if (!flagAlias){
                    sb.append(queryColums.getColumnAlias()+".");
                }
            }
            sb.append(queryColums.getField()+" ");
            flagAlias = true;
        }
        return sb.toString();
    }

    private String wapperWhere(DroplistVo param){
        StringBuffer sb = new StringBuffer();
        DroplistConditionsVo conditionsVo;
        boolean flagFirst = true;
        boolean flagAlias = false;
        boolean flagValue = false;
        for (int i = 0,size = param.getConditions().size(); i < size; i++) {
            conditionsVo = param.getConditions().get(i);
            if (StringUtils.isNotEmpty(conditionsVo.getFieldAlias())){
                flagAlias = true;
            }
            // 字段没有值该查询条件没有
            if (StringUtils.isNotEmpty(conditionsVo.getValue())){
                flagValue = true;
            }else{
                flagValue = false;
            }
            if(flagValue){
                //判断是否第一个 第一个不需要拼接 and 或者 or 后面的需要
                if(flagFirst){
                    sb.append(" where ");
                    flagFirst = false;
                }else {
                    sb.append(conditionsVo.getLogicOpt()+ " ");
                }
                // 判断是否需要凭借 字段别名
                if (flagAlias){
                    sb.append(conditionsVo.getFieldAlias()+".");
                }
                // 拼接字段
                sb.append(conditionsVo.getField()+" ");
                if("like".equalsIgnoreCase(conditionsVo.getCompareOpt())){
                    sb.append(" like '"+ conditionsVo.getValue() +"%' ");
                }else if ("in".equalsIgnoreCase(conditionsVo.getCompareOpt())){
                    sb.append(conditionsVo.getCompareOpt()+" "+ conditionsVo.getValue() +" ");
                }else {
                    sb.append(conditionsVo.getCompareOpt()+" '"+ conditionsVo.getValue() +"' ");
                }
            }

        }
        return sb.toString();
    }

    private String wapperLimit(DroplistVo param){
        StringBuffer sb = new StringBuffer();
        sb.append(" limit "+(Integer.valueOf(param.getPage())-1)*Integer.valueOf(param.getRows())+","+(Integer.valueOf(param.getRows())));
        return sb.toString();
    }
}
