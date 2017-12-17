package com.ranran.core;

import com.ranran.core.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 基础控制类
 *
 * @author 曾睿
 * @create 2017-07-25 21:26
 **/
public class RestBaseController {


    public ResponseResult saveResult(int i) {
        ResponseResult resultBody = new ResponseResult();
        if (i <= 0 ){
            resultBody.success = false;
            resultBody.message = "保存失败！";
        }else{
            resultBody.success = true;
            resultBody.message = "保存成功！";
        }
        return resultBody;
    }

    public ResponseResult optResult(int i) {
        ResponseResult resultBody = new ResponseResult();
        if (i <= 0 ){
            resultBody.success = false;
            resultBody.message = "操作失败！";
        }else{
            resultBody.success = true;
            resultBody.message = "操作成功！";
        }
        return resultBody;
    }

    public ResponseResult deleteResult(int i) {
        ResponseResult resultBody = new ResponseResult();
        if (i <= 0 ){
            resultBody.success = false;
            resultBody.message = "删除失败！";
        }else{
            resultBody.success = true;
            resultBody.message = "删除成功！";
        }
        return resultBody;
    }


    public ResponseResult listResult(List list) {
        ResponseResult resultBody = new ResponseResult();
        resultBody.success = true;
        resultBody.data = list;
        if (list.size()>0){
            resultBody.total = (Long.valueOf(list.size()));
            resultBody.rows = list.toArray();
        }else {
            resultBody.total = (0L);
            resultBody.rows = new Object[]{};
        }
        return resultBody;
    }

    public ResponseResult objectResult(Object object) {
        ResponseResult resultBody = new ResponseResult();
        resultBody.success = true;
        resultBody.data = object;
        return resultBody;
    }

    public String wrapperJson(HttpServletRequest request,ErrorCode errorCode){
        String reqData = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8")) ;
            if(br!= null){
                String out = null;
                StringBuilder sb = new StringBuilder();
                while((out = br.readLine()) != null){
                    sb.append(out);
                }
                br.close();
                reqData = sb.toString();
            }
        } catch (Exception e) {
            new ControllerException(errorCode);
        }
        return reqData;
    }
}
