package com.ranran.core;

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
            resultBody.setSuccess(false);
            resultBody.setMessage("保存失败！");
        }else{
            resultBody.setSuccess(true);
            resultBody.setMessage("保存成功！");
        }
        return resultBody;
    }

    public ResponseResult optResult(int i) {
        ResponseResult resultBody = new ResponseResult();
        if (i <= 0 ){
            resultBody.setSuccess(false);
            resultBody.setMessage("操作失败！");
        }else{
            resultBody.setSuccess(true);
            resultBody.setMessage("操作成功！");
        }
        return resultBody;
    }

    public ResponseResult deleteResult(int i) {
        ResponseResult resultBody = new ResponseResult();
        if (i <= 0 ){
            resultBody.setSuccess(false);
            resultBody.setMessage("删除失败！");
        }else{
            resultBody.setSuccess(true);
            resultBody.setMessage("删除成功！");
        }
        return resultBody;
    }


    public ResponseResult listResult(List list) {
        ResponseResult resultBody = new ResponseResult();
        resultBody.setSuccess(true);
        resultBody.setResultData(list);
        if (list.size()>0){
            resultBody.setTotal(Long.valueOf(list.size()));
            resultBody.setRows(list.toArray());
        }else {
            resultBody.setTotal(0L);
            resultBody.setRows(new Object[]{});
        }
        return resultBody;
    }

    public ResponseResult objectResult(Object object) {
        ResponseResult resultBody = new ResponseResult();
        resultBody.setSuccess(true);
        resultBody.setResultData(object);
        return resultBody;
    }
}
