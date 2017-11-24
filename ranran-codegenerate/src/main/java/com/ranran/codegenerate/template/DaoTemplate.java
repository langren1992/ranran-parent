package com.ranran.codegenerate.template;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class DaoTemplate extends TemplateInfo {

    private String suffix = "Dao.java";

    private String packagePath = "dao";

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
        return "dao.btl";
    }

}
