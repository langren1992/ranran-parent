package com.ranran.codegenerate;

/**
 * 生成信息
 *
 * @author 曾睿
 * @create 2017-11-23 16:19
 **/
public class GenerateInfo {

    private String tablePattern;

    private String projectUrl;

    private String packageUrl;

    private String modelName;

    public GenerateInfo() {
    }

    public GenerateInfo(String tablePattern, String projectUrl, String packageUrl, String modelName) {
        this.tablePattern = tablePattern;
        this.projectUrl = projectUrl;
        this.packageUrl = packageUrl;
        this.modelName = modelName;
    }

    public String getTablePattern() {
        return tablePattern;
    }

    public void setTablePattern(String tablePattern) {
        this.tablePattern = tablePattern;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
