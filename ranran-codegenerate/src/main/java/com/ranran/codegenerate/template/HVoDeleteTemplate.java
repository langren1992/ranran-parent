package com.ranran.codegenerate.template;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class HVoDeleteTemplate extends TemplateInfo {

    private String suffix = "DeleteVo.java";

    private String packagePath = "operate/vo";

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
        return "h-java-vo-delete.btl";
    }

}
