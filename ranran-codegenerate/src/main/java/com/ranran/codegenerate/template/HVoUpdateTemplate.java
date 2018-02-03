package com.ranran.codegenerate.template;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class HVoUpdateTemplate extends TemplateInfo {

    private String suffix = "UpdateVo.java";

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
        return "h-java-vo-update.btl";
    }

}
