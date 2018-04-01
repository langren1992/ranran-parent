package com.ranran.sql.service.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.util.JdbcConstants;
import com.ranran.core.ResponseResult;
import com.ranran.sql.entity.*;
import com.ranran.sql.mapper.SqlParseMapper;
import com.ranran.sql.model.ColumnModel;
import com.ranran.sql.model.TableModel;
import com.ranran.sql.service.SqlParseService;
import com.ranran.sql.vo.SqlParseVo;
import com.ranran.sql.vo.TableColumnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class SqlParseServiceImpl implements SqlParseService{

    @Autowired
    private SqlParseMapper sqlParseMapper;

    @Override
    public SqlSelectEntity parseSql(SqlParseVo sqlParseVo) {
        //使用mysql解析
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(SQLUtils.format(sqlParseVo.getSql(), JdbcConstants.MYSQL)) ;
        //解析select查询
        SQLSelectStatement sqlStatement = (SQLSelectStatement) sqlStatementParser.parseSelect();
        SQLSelect sqlSelect = sqlStatement.getSelect() ;
        //获取sql查询块
        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock)sqlSelect.getQuery();
        SqlSelectEntity sqlSelectEntity = new SqlSelectEntity();

        sqlSelectEntity.setSelectColumns(parsrSelectColumns(sqlSelectQuery.getSelectList()));
        /**
         * select from table parse
         */
        FromTableEntity mainFromTableEntity = new FromTableEntity();
        //join table parse
        List<FromTableEntity> joinFromTableEntityList = new ArrayList<FromTableEntity>();
        exprFromTable(mainFromTableEntity,joinFromTableEntityList,sqlSelectQuery.getFrom());
        sqlSelectEntity.setMainFromTable(mainFromTableEntity);
        sqlSelectEntity.setJoinFromTables(joinFromTableEntityList);

        //where parse
        List<FromWhereEntity> fromWhereEntityList = new ArrayList<FromWhereEntity>();
        exprFromWhere(fromWhereEntityList,sqlSelectQuery.getWhere());
        sqlSelectEntity.setFromWheres(fromWhereEntityList);

        //group by parse
        sqlSelectEntity.setSelectGroupBy(parseGroupBy(sqlSelectQuery.getGroupBy()));

        //order by parse
        sqlSelectEntity.setSelectOrderBy(parseOrderBy(sqlSelectQuery.getOrderBy()));

        //limit parse
        sqlSelectEntity.setSelectLimit(parseLimit(sqlSelectQuery.getLimit()));
        return null;
    }

    @Override
    public ResponseResult selectTables(TableColumnVo tableColumnVo) {
        ResponseResult result = new ResponseResult();
        TableModel tableModel = new TableModel();
        tableModel.setTableName(tableColumnVo.getTableName());
        result.data = sqlParseMapper.selectTables(tableModel);
        return result;
    }

    @Override
    public ResponseResult selectTableColumns(TableColumnVo tableColumnVo) {
        ResponseResult result = new ResponseResult();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setTableName(tableColumnVo.getTableName());
        result.data = sqlParseMapper.selectTableColumns(columnModel);
        return result;
    }

    private SelectLimitEntity parseLimit(SQLLimit limit) {
        SelectLimitEntity selectLimitEntity = new SelectLimitEntity();
        if (limit != null){
            if (limit.getOffset()!=null){
                selectLimitEntity.setOffset(limit.getOffset().toString());
            }
            selectLimitEntity.setRowCount(limit.getRowCount().toString());
        }
        return selectLimitEntity;
    }

    private static SelectOrderByEntity parseOrderBy(SQLOrderBy orderBy){
        //order by parse
        StringBuffer sb = new StringBuffer();
        SelectOrderByEntity orderByEntity = new SelectOrderByEntity();
        if (orderBy != null){
            List<SQLSelectOrderByItem> items = orderBy.getItems();
            boolean flag = true;
            for (int i = 0,size = items.size(); i < size; i++) {
                if(flag){
                    sb.append(items.get(i).getExpr().toString());
                    flag = false;
                }else {
                    sb.append(",");
                    sb.append(items.get(i).getExpr().toString());
                }
            }
            orderByEntity.setOrderBy(sb.toString());
        }
        return orderByEntity;
    }

    private SelectGroupByEntity parseGroupBy(SQLSelectGroupByClause groupBy){
        StringBuffer groupSb = new StringBuffer();
        SelectGroupByEntity groupByEntity = new SelectGroupByEntity();
        if (groupBy != null){
            if (groupBy.getHaving()!=null){
                groupByEntity.setHaving(groupBy.getHaving().toString());
            }
            List<SQLExpr> items = groupBy.getItems();
            boolean flag = true;
            for (int i = 0,size = items.size(); i < size; i++) {
                if(flag){
                    groupSb.append(((SQLPropertyExpr)items.get(i)).toString());
                    flag = false;
                }else {
                    groupSb.append(",");
                    groupSb.append(((SQLPropertyExpr)items.get(i)).toString());
                }
            }
            groupByEntity.setGroupBy(groupSb.toString());
        }
        return groupByEntity;
    }

    private List<SelectColumnEntity> parsrSelectColumns(List<SQLSelectItem> selectList){
        //select column parse
        List<SelectColumnEntity> columnEntityList = new ArrayList<SelectColumnEntity>(selectList.size());
        SelectColumnEntity columnEntity;
        for (SQLSelectItem sqlSelectItem : selectList) {
            columnEntity = new SelectColumnEntity();
            SQLExpr expr = sqlSelectItem.getExpr();
            if(expr instanceof SQLPropertyExpr){
                SQLPropertyExpr propertyExpr = (SQLPropertyExpr) expr;
                columnEntity.setName(propertyExpr.getName());
                columnEntity.setAlias(propertyExpr.getOwner().toString());
            }else if (expr instanceof SQLAggregateExpr){
                SQLAggregateExpr aggregateExpr = (SQLAggregateExpr) expr;
                columnEntity.setName(aggregateExpr.toString());
                columnEntity.setAlias(sqlSelectItem.getAlias());
            }else {
                SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) expr;
                columnEntity.setName(identifierExpr.getName());
            }
            columnEntityList.add(columnEntity);
        }
        return columnEntityList;
    }

    private void exprFromWhere(List<FromWhereEntity> fromWhereEntityList,SQLExpr sqlExpr){
        FromWhereEntity fromWhereEntity;
        if (sqlExpr instanceof SQLBinaryOpExpr){
            SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlExpr;
            exprFromWhere(fromWhereEntityList,sqlBinaryOpExpr.getLeft());
            exprFromWhere(fromWhereEntityList,sqlBinaryOpExpr.getRight());
        }else if (sqlExpr instanceof SQLInListExpr){
            SQLInListExpr sqlInListExpr = (SQLInListExpr) sqlExpr;
            SQLBinaryOpExpr parent = (SQLBinaryOpExpr)sqlInListExpr.getParent();
            fromWhereEntity = new FromWhereEntity();
            fromWhereEntity.setColumn(sqlInListExpr.getExpr().toString());
            fromWhereEntity.setCompareType("in");
            fromWhereEntity.setValue("("+ Arrays.toString(sqlInListExpr.getTargetList().toArray()).replace("[","").replace("]","")+")");
            fromWhereEntity.setLogicType(parent.getOperator().name_lcase);
            fromWhereEntityList.add(fromWhereEntity);
        }else if (sqlExpr instanceof SQLBetweenExpr){
            SQLBetweenExpr sqlBetweenExpr = (SQLBetweenExpr) sqlExpr;
            SQLBinaryOpExpr parent = (SQLBinaryOpExpr)sqlBetweenExpr.getParent();
            fromWhereEntity = new FromWhereEntity();
            fromWhereEntity.setColumn(sqlBetweenExpr.getTestExpr().toString());
            fromWhereEntity.setCompareType("between");
            fromWhereEntity.setValue(sqlBetweenExpr.getBeginExpr()+" and "+sqlBetweenExpr.getEndExpr());
            fromWhereEntity.setLogicType(parent.getOperator().name_lcase);
            fromWhereEntityList.add(fromWhereEntity);
        }else if (sqlExpr instanceof SQLIdentifierExpr){
            SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) sqlExpr;
            SQLBinaryOpExpr parent = (SQLBinaryOpExpr) sqlIdentifierExpr.getParent();
            fromWhereEntity = new FromWhereEntity();
            if (parent.getParent() instanceof SQLBinaryOpExpr){
                SQLBinaryOpExpr gParent = (SQLBinaryOpExpr)parent.getParent();
                fromWhereEntity.setLogicType(gParent.getOperator().name_lcase);
            }
            fromWhereEntity.setColumn(parent.getLeft().toString());
            fromWhereEntity.setCompareType(parent.getOperator().name_lcase);
            fromWhereEntity.setValue(parent.getRight().toString());
            fromWhereEntityList.add(fromWhereEntity);
        }else if (sqlExpr instanceof SQLPropertyExpr){
            SQLPropertyExpr sqlPropertyExpr = (SQLPropertyExpr) sqlExpr;
            SQLBinaryOpExpr parent = (SQLBinaryOpExpr) sqlPropertyExpr.getParent();
            fromWhereEntity = new FromWhereEntity();
            if (parent.getParent() instanceof SQLBinaryOpExpr){
                SQLBinaryOpExpr gParent = (SQLBinaryOpExpr)parent.getParent();
                fromWhereEntity.setLogicType(gParent.getOperator().name_lcase);
            }
            fromWhereEntity.setColumn(parent.getLeft().toString());
            fromWhereEntity.setCompareType(parent.getOperator().name_lcase);
            fromWhereEntity.setValue(parent.getRight().toString());
            fromWhereEntityList.add(fromWhereEntity);
        }

    }

    /**
     * @Title exprFromTable
     * @description TODO
     * @param mainFromTableEntity
     * @param joinFromTableEntityList
     * @param fromTableSource
     * @author zengrui
     * @createTime 2018/4/1 18:28
     * @modifyTime 2018/4/1 18:28
     * @return void
     **/
    private void exprFromTable(FromTableEntity mainFromTableEntity,List<FromTableEntity> joinFromTableEntityList,SQLTableSource fromTableSource){
        String tableAlias = fromTableSource.getAlias();
        if (fromTableSource instanceof SQLExprTableSource) {
            SQLExprTableSource sqlExprTableSource = (SQLExprTableSource) fromTableSource;
            if (sqlExprTableSource.getExpr() instanceof SQLIdentifierExpr){
                SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) sqlExprTableSource.getExpr();
                mainFromTableEntity.setAlias(tableAlias);
                mainFromTableEntity.setName(sqlIdentifierExpr.getName());
                mainFromTableEntity.setJoinOrMain("main");
            }
        }else if (fromTableSource instanceof  SQLJoinTableSource){
            SQLJoinTableSource sqlJoinTableSource = (SQLJoinTableSource) fromTableSource;
            FromTableEntity fromTable = new FromTableEntity();
            fromTable.setName(sqlJoinTableSource.getRight().toString());
            fromTable.setAlias(sqlJoinTableSource.getRight().getAlias());
            fromTable.setJoinType(sqlJoinTableSource.getJoinType().name_lcase);
            fromTable.setJoinKey(sqlJoinTableSource.getCondition().toString());
            fromTable.setJoinOrMain("join");
            joinFromTableEntityList.add(fromTable);
            SQLTableSource left = sqlJoinTableSource.getLeft();
            exprFromTable(mainFromTableEntity,joinFromTableEntityList,left);
        }
    }
}
