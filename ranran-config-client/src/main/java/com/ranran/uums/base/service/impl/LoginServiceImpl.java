package com.ranran.uums.base.service.impl;

import com.ranran.uums.base.mapper.LoginMapper;
import com.ranran.uums.base.service.LoginService;
import com.ranran.uums.system.mapper.RaUserRoleMapper;
import com.ranran.uums.system.model.RaUserRole;
import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<TsResource> loadResourceMenu(TsUser tsUser) {
        List<TsResource> userForResourceList = loginMapper.selectUserForResource(tsUser.getUserNo());
        return userForResourceList;
    }
}
