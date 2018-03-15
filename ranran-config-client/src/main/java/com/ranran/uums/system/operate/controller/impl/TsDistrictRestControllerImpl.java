package com.ranran.uums.system.operate.controller.impl;

import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsDistrictRestController;
import com.ranran.uums.system.operate.vo.TsDistrictSelectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求控制类
 * @creator zengrui 2018-02-03 05:27
 */
@RestController(value = "TsDistrictRestController")
@RequestMapping("/tsDistrict")
public class TsDistrictRestControllerImpl extends RestBaseController implements TsDistrictRestController {

    private static Logger LOGGER = LoggerFactory.getLogger(TsDistrictRestControllerImpl.class);

    @Autowired
    private TsDistrictRestController tsDistrictRestController;

    /**
     * 查询信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectTsDistrict.html")
    public ResponseResult selectTsDistrict(HttpServletRequest request) {
        return tsDistrictRestController.selectTsDistrict(request);
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateTsDistricts.html")
    public ResponseResult updateTsDistricts(HttpServletRequest request) {
        return tsDistrictRestController.updateTsDistricts(request);
    }

    /**
     * 删除（物理删除）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/deleteTsDistricts.html")
    public ResponseResult deleteTsDistricts(HttpServletRequest request) {
        return tsDistrictRestController.deleteTsDistricts(request);
    }

    /**
     * 导入
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/importTsDistricts.html")
    public ResponseResult importTsDistricts(HttpServletRequest request) {
        return tsDistrictRestController.importTsDistricts(request);
    }

    /**
     * 导出
     *
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/exportTsDistricts.html")
    public void exportTsDistricts(TsDistrictSelectVo tsDistrictSelectVo, HttpServletResponse response) {
        tsDistrictRestController.exportTsDistricts(tsDistrictSelectVo,response);
    }

    /**
     * 导出
     *
     * @param response 响应结果
     */
    @Override
    @PostMapping("/downloadTsDistrict.html")
    public void downloadTsDistrict(HttpServletResponse response) {
        tsDistrictRestController.downloadTsDistrict(response);
    }

    /**
     * 通过第三方获取省市区县信息 高德（IMAP）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/syncMapTsDistrict.html")
    public ResponseResult syncMapTsDistrict(HttpServletRequest request) {
        return tsDistrictRestController.syncMapTsDistrict(request);
    }

    /**
     * 省市区县级联查询
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/getProvCityDist.html")
    public ResponseResult getProvCityDist(HttpServletRequest request) {
        return tsDistrictRestController.getProvCityDist(request);
    }
}
