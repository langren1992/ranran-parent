package com.ranran.uums.system.operate.vo;


import com.ranran.uums.system.model.TsResource;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22 0022.
 */
public class TsResourceVo {

    private TsResource tsResource;

    public TsResource getTsResource() {
        return tsResource;
    }

    public void setTsResource(TsResource tsResource) {
        this.tsResource = tsResource;
    }

    private List<TsResource> resBtnList;

    public List<TsResource> getResBtnList() {
        return resBtnList;
    }

    public void setResBtnList(List<TsResource> resBtnList) {
        this.resBtnList = resBtnList;
    }
}
