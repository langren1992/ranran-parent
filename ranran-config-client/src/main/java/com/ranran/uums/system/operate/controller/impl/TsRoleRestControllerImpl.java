package com.ranran.uums.system.operate.controller.impl;


import com.ranran.core.ErrorCode;
import com.ranran.core.RestBaseController;
import com.ranran.core.ResponseResult;
import com.ranran.core.exception.ControllerException;
import com.ranran.core.exception.ServiceException;
import com.ranran.uums.system.operate.controller.TsRoleRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zengrui on 2017/7/11.
 */
@RestController(value = "TsRoleRestController")
@RequestMapping("/tsRole")
public class TsRoleRestControllerImpl extends RestBaseController implements TsRoleRestController{

    private static Logger LOGGER = LoggerFactory.getLogger(TsRoleRestControllerImpl.class);

    @Autowired
    private TsRoleRestController tsRoleRestController;

    /**
     * 根据条件查询功能和排序功能
     *
     * @param request
     * @return PageInfo
     */
    @PostMapping("/selectByCondition.html")
    public ResponseResult pageRoleCondition(HttpServletRequest request){
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.pageRoleCondition(request);
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
     * 查询角色资源
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/selectRoleResource.html")
    public ResponseResult selectRoleResource(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.selectRoleResource(request);
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
     * 查询角色信息下的资源权限
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/selectRoleResPermi.html")
    public ResponseResult selectRoleResPermi(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.selectRoleResPermi(request);
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
     * 查询角色下的所有用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    @PostMapping("/selectRoleUser.html")
    public ResponseResult selectRoleUser(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.selectRoleUser(request);
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
     * 查询所有不属于该角色的用户
     *
     * @param request
     * @return ResponseResult
     */
    @Override
    @PostMapping("/selectRoleNotUser.html")
    public ResponseResult selectRoleNotUser(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.selectRoleNotUser(request);
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
     * 新增，更新角色信息
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/updateRoles.html")
    public ResponseResult updateRoleBatch(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.updateRoleBatch(request);
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
     * 删除角色
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/deleteRole.html")
    public ResponseResult deleteRole(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.deleteRole(request);
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
     * 生成角色资源、权限关联关系
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/optRoleResRal.html")
    public ResponseResult optRoleResRal(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.optRoleResRal(request);
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
     * 生成角色权限关联关系
     *
     * @param request 请求参数
     * @return ResponseResult
     */
    @Override
    @PostMapping("/optRoleResPermiRal.html")
    public ResponseResult optRoleResPermiRal(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.optRoleResPermiRal(request);
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
     * 生成角色用户关联关系
     *
     * @param request
     * @return
     */
    @Override
    @PostMapping("/optRoleUserRal.html")
    public ResponseResult optRoleUserRal(HttpServletRequest request) {
        ResponseResult responseResult = null;
        try {
            responseResult = tsRoleRestController.optRoleUserRal(request);
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


}
