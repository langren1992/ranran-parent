package com.ranran.uums.base.controller;

import com.ranran.core.ResponseResult;
import com.ranran.core.shiro.bind.annotation.CurrentUser;
import com.ranran.uums.base.service.LoginService;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {

    //日志
    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/index.html")
    public String index(){
        return "index.html";
    }

    @RequestMapping("/indexMain.html")
    public String indexMain(){
        return "main.html";
    }

    @RequestMapping(value="/login.html",method= RequestMethod.GET)
    public String login(){
        return "login.html";
    }

    @RequestMapping(value="/doLogin.html",method= RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(String username,String password,boolean rememberMe){
        ResponseResult responseResult = new ResponseResult();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //错误消息提醒
        String errorMsg = "";
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            LOGGER.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            LOGGER.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException e){
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            errorMsg = "未知账户";
            e.printStackTrace();
        }catch(IncorrectCredentialsException e){
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            errorMsg = "密码不正确";
            e.printStackTrace();
        }catch(LockedAccountException e){
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            errorMsg = "账户已锁定";
            e.printStackTrace();
        }catch(ExcessiveAttemptsException e){
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            errorMsg = "用户名或密码错误次数过多";
            e.printStackTrace();
        }catch(AuthenticationException e){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            LOGGER.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            errorMsg = "用户名或密码不正确";
            e.printStackTrace();
        }
        if (!"".equals(errorMsg)&& null!= errorMsg){
            responseResult.setSuccess(false);
            responseResult.setMessage(errorMsg);
        }else {
            responseResult.setSuccess(true);
        }
        return responseResult;
    }

    @Autowired
    private LoginService loginService;

    @GetMapping("/menu.html")
    @ResponseBody
    public ResponseResult resourceMenu(@CurrentUser TsUser tsUser){
        ResponseResult responseResult = new ResponseResult();
        List<TsResource> menus = loginService.loadResourceMenu(tsUser);
        try {
            responseResult.setSuccess(true);
            responseResult.setResultData(menus);
        } catch (Exception e){
            LOGGER.error("operate/system/tsResource/menu fail cause by : {}",e.getMessage());
            responseResult.setSuccess(false);
            responseResult.setMessage(e.getMessage());
        }
        return responseResult;
    }

    @RequestMapping(value="/logout.html",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
}