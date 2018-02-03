package com.ranran.codegenerate.template;

import com.ranran.codegenerate.StringUtils;

/**
 * JS模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:35
 **/
public class HMapperXmlTemplate extends TemplateInfo {

    private String suffix = ".xml";

    private String packagePath = "mapper/xml";

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
        return "h-xml-mapper.btl";
    }

    @Override
    public String getFileName() {
        return StringUtils.toLowerCaseFirstOne(super.getClassName())+suffix;
    }
}
