package com.ranran.codegenerate.template;

import com.ranran.codegenerate.StringUtils;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class JsTemplate extends TemplateInfo {

    private String suffix = ".js";

    private String packagePath = "js";

    public String getSuffix() {
        return suffix;
    }

    public String getFunctionPath() {
        return packagePath;
    }

    @Override
    public BrowserOrServer getIsBrowserOrServer() {
        return BrowserOrServer.BROWSER;
    }

    @Override
    public String getTemplateName() {
        return "js.btl";
    }

    @Override
    public String getFileName() {
        return StringUtils.toLowerCaseFirstOne(super.getClassName())+suffix;
    }
}
