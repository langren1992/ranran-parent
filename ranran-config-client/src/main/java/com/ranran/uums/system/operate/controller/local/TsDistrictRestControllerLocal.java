package com.ranran.uums.system.operate.controller.local;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ranran.core.ControllerException;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.uums.system.operate.controller.TsDistrictRestController;
import com.ranran.uums.system.operate.service.TsDistrictService;
import com.ranran.uums.system.operate.vo.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
/**
 * 请求处理
 * @creator zengrui 2018-01-30 10:13
 */
@Component
public class TsDistrictRestControllerLocal extends RestBaseController implements TsDistrictRestController {

    @Autowired
    private TsDistrictService tsDistrictService;

    /**
     * 查询信息
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult selectTsDistrict(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsDistrict/selectTsDistrict.html"));
        TsDistrictSelectVo tsDistrictSelectVo = JSONObject.parseObject(reqData,TsDistrictSelectVo.class);
        PageInfo pageInfo = tsDistrictService.selectTsDistrict(tsDistrictSelectVo);
        ResponseResult resultBody = new ResponseResult();
        if (pageInfo.getList().size() >0){
            resultBody.rows = (pageInfo.getList().toArray());
            resultBody.total = pageInfo.getTotal();
        }else {
            resultBody.rows = new Object[]{};
            resultBody.total = pageInfo.getTotal();
        }
        return resultBody;
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult updateTsDistricts(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsDistrict/updateTsDistrict.html"));
        List<TsDistrictUpdateVo> tsDistrictUpdateVos = JSONArray.parseArray(reqData,TsDistrictUpdateVo.class);
        return this.saveResult(tsDistrictService.updateTsDistricts(tsDistrictUpdateVos));
    }

    /**
     * 删除（物理删除）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult deleteTsDistricts(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(103,"/tsDistrict/deleteSystemControls.html"));
        List<TsDistrictDeleteVo> tsDistrictDeleteVos = JSONArray.parseArray(reqData,TsDistrictDeleteVo.class);
        return this.deleteResult(tsDistrictService.deleteTsDistricts(tsDistrictDeleteVos));
    }

    /**
     * 导入
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult importTsDistricts(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");
            if(file.isEmpty()){
                throw new ControllerException(new ErrorCode(107,"文件不存在！"));
            }
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<TsDistrictImportVo> objects = ExcelImportUtil.importExcel(file.getInputStream(), TsDistrictImportVo.class,params);
            return tsDistrictService.importTsDistricts(objects);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(108,"编码格式异常！"));
        }
    }

    /**
     * 导出
     *
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    @Override
    public void exportTsDistricts(@RequestParam TsDistrictSelectVo tsDistrictSelectVo, HttpServletResponse response){
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("行政区划-导出数据","UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            List<TsDistrictExportVo> tsDistrictExportVos = tsDistrictService.exportTsDistricts(tsDistrictSelectVo);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TsDistrictExportVo.class, tsDistrictExportVos);
            workbook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(104,"编码格式异常！"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(105,"出现I/O异常！"));
        }
    }

    /**
     * 导出
     *
     * @param response 响应结果
     */
    @Override
    public void downloadTsDistrict(HttpServletResponse response) {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("行政区划-导入模板","UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            List<TsDistrictExportVo> tsDistrictExportVos = new ArrayList<TsDistrictExportVo>();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TsDistrictExportVo.class, tsDistrictExportVos);
            workbook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(106,"编码格式异常！"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(107,"出现I/O异常！"));
        }
    }

    /**
     * 通过第三方获取省市区县信息 高德（IMAP）
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult syncMapTsDistrict(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(110,"/tsDistrict/updateTsDistrict.html"));
        TsDistrictSyncMapVo tsDistrictSyncMapVo = JSONObject.parseObject(reqData,TsDistrictSyncMapVo.class);
        return this.saveResult(tsDistrictService.syncMapTsDistrict(tsDistrictSyncMapVo));
    }

    /**
     * 省市区县级联查询
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult getProvCityDist(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(111,"/tsDistrict/getProvCityDist.html"));
        TsDistrictProvCityDistVo tsDistrictProvCityDistVo = JSONObject.parseObject(reqData,TsDistrictProvCityDistVo.class);
        return this.listResult(tsDistrictService.getProvCityDist(tsDistrictProvCityDistVo));
    }
}