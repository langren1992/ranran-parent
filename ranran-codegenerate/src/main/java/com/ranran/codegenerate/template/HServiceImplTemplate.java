package com.ranran.codegenerate.template;

/**
 * 服务类模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class HServiceImplTemplate extends TemplateInfo {

    private String suffix = "ServiceImpl.java";

    private String packagePath = "operate/service/impl";

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
        return "h-java-service-impl.btl";
    }
}
