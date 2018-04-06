package com.ranran.system.operate.controller.local;

import com.alibaba.fastjson.JSONObject;
import com.ranran.core.ErrorCode;
import com.ranran.core.ResponseResult;
import com.ranran.core.RestBaseController;
import com.ranran.system.operate.controller.DroplistController;
import com.ranran.system.operate.service.DroplistService;
import com.ranran.system.operate.vo.DroplistVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DroplistControllerLocal extends RestBaseController implements DroplistController {

    protected Logger LOGGER = LoggerFactory.getLogger(DroplistControllerLocal.class);

    @Autowired
    private DroplistService droplistService;


    /**
     * @param request HttpServletRequest
     * @return ResponseResult
     * @Title queryDropListData
     * @description 查询下拉列表数据
     * @author zengrui
     * @createTime 2018/4/6 21:36
     * @modifyTime 2018/4/6 21:36
     **/
    @Override
    public ResponseResult queryDropListData(HttpServletRequest request) {
        String reqData = this.wrapperJson(request, new ErrorCode(101,"/droplist/queryDropListData.html"));
        DroplistVo droplistVo = JSONObject.parseObject(reqData,DroplistVo.class);
        return droplistService.queryDropListData(droplistVo);
    }
}
