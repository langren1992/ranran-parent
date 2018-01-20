package com.ranran.uums.system.operate.controller.impl;

import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.core.exception.ControllerException;
import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.operate.controller.TsUserRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/9.
 */
@RestController(value = "TsUserRestController")
@RequestMapping("/tsUser")
public class TsUserRestControllerImpl extends RestBaseController implements TsUserRestController {

    private static Logger LOGGER = LoggerFactory.getLogger(TsUserRestControllerImpl.class);

    @Autowired
    private TsUserRestController tsUserRestController;

    /**
     * 查询部门信息，生成树形菜单
     *
     * @param request 请求参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/selectUser.html")
    public ResponseResult selectUser(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsUserRestController.selectUser(request);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            ErrorCode error = e.getErrorCode();
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = error.code +":"+error.name;
        } catch (ControllerException e) {
            LOGGER.error(e.getMessage(), e);
            ErrorCode error = e.getErrorCode();
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = error.code +":"+error.name;
        }  catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = "999:未知错误,请联系管理员!";
        }
        return responseResult;
    }

    /**
     * 新增、启用、停用、删除（逻辑阐述）部门
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateUsers.html")
    public ResponseResult updateUsers(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsUserRestController.updateUsers(request);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            ErrorCode error = e.getErrorCode();
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = error.code +":"+error.name;
        } catch (ControllerException e) {
            LOGGER.error(e.getMessage(), e);
            ErrorCode error = e.getErrorCode();
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = error.code +":"+error.name;
        }  catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseResult = new ResponseResult();
            responseResult.success = false;
            responseResult.message = "999:未知错误,请联系管理员!";
        }
        return responseResult;
    }

    /**
     * 初始化密码
     *
     * @param request 参数
     * @return ResponseResult 响应结果
     */
    @Override
    @PostMapping("/updateUserPwd.html")
    public ResponseResult updateUserPwd(HttpServletRequest request) {
        return tsUserRestController.updateUserPwd(request);
    }
}
