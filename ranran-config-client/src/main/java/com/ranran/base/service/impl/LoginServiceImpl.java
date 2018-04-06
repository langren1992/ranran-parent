package com.ranran.base.service.impl;

import com.ranran.base.mapper.LoginResMapper;
import com.ranran.base.service.LoginService;
import com.ranran.system.model.TsResource;
import com.ranran.system.model.TsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginResMapper loginResMapper;

    @Override
    public List<TsResource> loadResourceMenu(TsUser tsUser) {
        List<TsResource> userForResourceList = loginResMapper.selectUserForResource(tsUser.getUserNo());
        return userForResourceList;
    }
}
