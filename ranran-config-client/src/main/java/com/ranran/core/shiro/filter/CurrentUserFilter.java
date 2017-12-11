package com.ranran.core.shiro.filter;

import com.ranran.core.shiro.util.Constants;
import com.ranran.uums.system.mapper.TsUserMapper;
import com.ranran.uums.system.model.TsUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zengrui on 2017/7/18.
 */
public class CurrentUserFilter extends PathMatchingFilter {

    @Autowired
    private TsUserMapper tsUserMapper;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String userNo = (String) SecurityUtils.getSubject().getPrincipal();
        if (userNo != null || "".equalsIgnoreCase(userNo)){
            if (SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER)==null){
                TsUser tsUser = new TsUser();
                tsUser.setUserNo(userNo);
                tsUser = tsUserMapper.selectOne(tsUser);
                //将当前线程获取的用户信息保存到Subject的Session中来全局管理
                SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER,tsUser);
                request.setAttribute(Constants.CURRENT_USER,tsUser);
            }
        }
        return true;
    }
}
