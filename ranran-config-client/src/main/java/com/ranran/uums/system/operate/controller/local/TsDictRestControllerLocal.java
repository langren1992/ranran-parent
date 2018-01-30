package com.ranran.uums.system.operate.controller.local;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsDictRestController;
import com.ranran.uums.system.operate.service.TsDictService;
import com.ranran.uums.system.operate.vo.TsDictSearchVo;
import com.ranran.uums.system.operate.vo.TsDictTsResourceSearchVo;
import com.ranran.uums.system.operate.vo.TsDictUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 数据字典请求处理
 * @creator zengrui 2018-01-21 11:14
 */
@Component
public class TsDictRestControllerLocal extends RestBaseController implements TsDictRestController {

    @Autowired
    private TsDictService tsDictService;

    /**
    * 查询部门信息，生成树形菜单
    *
    * @param request 请求参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult selectTsDict(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/TsDict/selectTsDict.html"));
        TsDictSearchVo tsDictSearchVo = JSONObject.parseObject(reqData,TsDictSearchVo.class);
        return this.listResult(tsDictService.selectTsDict(tsDictSearchVo));
    }

    /**
     * 数据字典资源信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult selectTsDictTsResource(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/TsDict/selectTsDictTsResource.html"));
        TsDictTsResourceSearchVo tsDictTsResourceSearchVo = JSONObject.parseObject(reqData,TsDictTsResourceSearchVo.class);
        return this.listResult(tsDictService.selectTsDictTsResource(tsDictTsResourceSearchVo));
    }

    /**
    * 新增、启用、停用、删除（逻辑阐述）部门
    *
    * @param request 参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult updateTsDicts(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(103,"/TsDict/updateTsDict.html"));
        List<TsDictUpdateVo> tsDictUpdateVos = JSONArray.parseArray(reqData,TsDictUpdateVo.class);
        return this.saveResult(tsDictService.updateTsDicts(tsDictUpdateVos));
    }

}
