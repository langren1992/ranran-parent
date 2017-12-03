package com.ranran.codegenerate.template;

/**
 * 服务类模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class ServiceBaseTemplate extends TemplateInfo {

    private String suffix = "ServiceBase.java";

    private String packagePath = "service/base";

    public String getSuffix() {
        return suffix;
    }

    public String getFunctionPath() {
        return packagePath;
    }

    @Override
    public BrowserOrServer getIsBrowserOrServer() {
        return BrowserOrServer.SERVER;
    }

    @Override
    public String getTemplateName() {
        return "service-base.btl";
    }
}
