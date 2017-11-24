package com.ranran.codegenerate.template;

import com.ranran.codegenerate.sql.DbMata;
import com.ranran.codegenerate.sql.TableInfo;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:18
 **/
public abstract class TemplateInfo {

    private String className;

    private BrowserOrServer  isBrowserOrServer;

    protected static String SERVER_URL =  "src/main/java";

    protected static String BROWSER_URL = "src/main/resources";

    protected static String PREFIX_URL = "templates";

    public String getPrefix(){
        return PREFIX_URL;
    }

    public abstract String getSuffix();

    public abstract String getFunctionPath();

    public String getServerUrl(){
        return SERVER_URL;
    }

    public String getBrowserUrl(){
        return BROWSER_URL;
    }

    public static enum BrowserOrServer {
        BROWSER, SERVER
    }

    public BrowserOrServer getIsBrowserOrServer() {
        return isBrowserOrServer;
    }

    public abstract String getTemplateName();

    public String getFileName(){
        return className+getSuffix();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
