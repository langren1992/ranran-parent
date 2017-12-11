package com.ranran.uums.base.service;

import com.ranran.uums.system.model.TsResource;
import com.ranran.uums.system.model.TsUser;

import java.util.List;

public interface LoginService {
    List<TsResource> loadResourceMenu(TsUser tsUser);
}
