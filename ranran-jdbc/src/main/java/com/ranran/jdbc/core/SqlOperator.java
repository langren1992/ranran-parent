package com.ranran.jdbc.core;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SqlOperator {

    public static String SELECT = " select ";

    public static String FROM = " from ";

    public static String WHERE = " WHERE ";

    public static String doWrappedSelectSql(Field[] fields){
        StringBuffer selectSql = new StringBuffer(SqlOperateType.SELECT.name);
        int i = 0;
        for (Field field: fields) {
            i++;
            if (i==fields.length){
                selectSql.append(field.getName());
            }else{
                selectSql.append(field.getName()+",");
            }
        }
        selectSql.append(SqlOperateType.FROM.name);
        return selectSql.toString();
    }

    /**
     * 生成where查询语句
     *
     * @param conditions
     * @return
     */
    public static String doWrappedWhereSql(SelectCondition[] conditions){
        StringBuffer selectSql = new StringBuffer();
        SelectCondition selectConditionTmp = null;
        for (int i = 0,length = conditions.length; i < length; i++) {
            selectConditionTmp =conditions[i];
            if (selectConditionTmp.getOperator() == ConditionOperateType.EQUAL){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" = '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.GREAT_THAN){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" > '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.LESS_THAN){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" < '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.GREAT_EQUAL){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" >= '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.LESS_EQUAL){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" <= '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.NOT_EQUAL){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" != '"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.BETWEEN){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" between '"+ selectConditionTmp.getLeftValue()+"' and '" + selectConditionTmp.getLeftValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.IN){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" in ("+ doGetArrayContent(selectConditionTmp.getValues())+") and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.LIKE){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" like '%"+ selectConditionTmp.getValue()+"%' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.LEFT_LIKE){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" like '%"+ selectConditionTmp.getValue()+"' and");
            }else if(selectConditionTmp.getOperator() == ConditionOperateType.RIGHT_LIKE){
                selectSql.append(" "+selectConditionTmp.getFieldName()+" = '"+ selectConditionTmp.getValue()+"%' and");
            }else{
                continue;
            }
        }

        return selectSql.substring(0, selectSql.length() - 4);
    }


    public static String doGetArrayContent(Object[] array){
        String s = Arrays.toString(array);
        return s.substring(1,s.length()-1);
    }

}
