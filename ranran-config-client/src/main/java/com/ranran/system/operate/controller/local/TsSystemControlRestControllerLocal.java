package com.ranran.system.operate.controller.local;

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
import com.ranran.system.operate.controller.TsSystemControlRestController;
import com.ranran.system.operate.service.TsSystemControlService;
import com.ranran.system.operate.vo.*;
import com.ranran.system.operate.vo.*;
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
 * 系统控制参数请求处理
 * @creator zengrui 2018-01-30 10:13
 */
@Component
public class TsSystemControlRestControllerLocal extends RestBaseController implements TsSystemControlRestController {

    @Autowired
    private TsSystemControlService tsSystemControlService;

    /**
    * 查询部门信息，生成树形菜单
    *
    * @param request 请求参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult selectTsSystemControl(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/tsSystemControl/selectTsSystemControl.html"));
        TsSystemControlSelectVo tsSystemControlSelectVo = JSONObject.parseObject(reqData,TsSystemControlSelectVo.class);
        PageInfo pageInfo = tsSystemControlService.selectTsSystemControl(tsSystemControlSelectVo);
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
    * 新增、启用、停用、删除（逻辑阐述）部门
    *
    * @param request 参数
    * @return ResponseResult 响应结果
    */
    @Override
    public ResponseResult updateTsSystemControls(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(102,"/tsSystemControl/updateTsSystemControl.html"));
        List<TsSystemControlUpdateVo> tsSystemControlUpdateVos = JSONArray.parseArray(reqData,TsSystemControlUpdateVo.class);
        return this.saveResult(tsSystemControlService.updateTsSystemControls(tsSystemControlUpdateVos));
    }

    /**
     * 删除（物理删除）系统控制参数
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult deleteTsSystemControls(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(103,"/tsSystemControl/deleteSystemControls.html"));
        List<TsSystemControlDeleteVo> tsSystemControlDeleteVos = JSONArray.parseArray(reqData,TsSystemControlDeleteVo.class);
        return this.deleteResult(tsSystemControlService.deleteTsSystemControls(tsSystemControlDeleteVos));
    }

    /**
     * 导入系统控制参数
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    public ResponseResult importTsSystemControls(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");
            if(file.isEmpty()){
                throw new ControllerException(new ErrorCode(107,"文件不存在！"));
            }
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<TsSystemControlImportVo> objects = ExcelImportUtil.importExcel(file.getInputStream(), TsSystemControlImportVo.class,params);
            return tsSystemControlService.importTsSystemControls(objects);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(108,"编码格式异常！"));
        }
    }

    /**
     * 导出系统控制参数
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    @Override
    public void exportTsSystemControls(@RequestParam TsSystemControlSelectVo tsSystemControlSelectVo, HttpServletResponse response){
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("系统控制参数表","UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            List<TsSystemControlExportVo> tsSystemControlExportVos = tsSystemControlService.exportTsSystemControls(tsSystemControlSelectVo);
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TsSystemControlExportVo.class, tsSystemControlExportVos);
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
     * 导出系统控制参数
     *
     * @param response 响应结果
     * @return ResponseResult 响应结果
     */
    @Override
    public void downloadTsSystemControl(HttpServletResponse response) {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("系统控制参数表","UTF-8") + ".xls");
            //编码
            response.setCharacterEncoding("UTF-8");
            List<TsSystemControlExportVo> tsSystemControlExportVos = new ArrayList<TsSystemControlExportVo>();
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), TsSystemControlExportVo.class, tsSystemControlExportVos);
            workbook.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(106,"编码格式异常！"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ControllerException(new ErrorCode(107,"出现I/O异常！"));
        }
    }

}
