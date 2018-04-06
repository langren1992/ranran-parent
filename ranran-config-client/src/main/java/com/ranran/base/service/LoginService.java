package com.ranran.base.service;

import com.ranran.system.model.TsResource;
import com.ranran.system.model.TsUser;

import java.util.List;

public interface LoginService {
    List<TsResource> loadResourceMenu(TsUser tsUser);
}
