package com.ranran.codegenerate.template;

import com.ranran.codegenerate.StringUtils;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class HMapperTemplate extends TemplateInfo {

    private String suffix = "Mapper.java";

    private String packagePath = "mapper";

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
        return "h-java-mapper.btl";
    }

}
