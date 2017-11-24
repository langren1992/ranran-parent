package com.ranran.codegenerate;

import com.ranran.codegenerate.sql.DbMata;
import com.ranran.codegenerate.sql.MysqlDataMeta;
import com.ranran.codegenerate.sql.TableInfo;
import com.ranran.codegenerate.template.*;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生产
 *
 * @author 曾睿
 * @create 2017-11-23 20:23
 **/
public class Coder {

    public void generate(GenerateInfo generateInfo) throws IOException, SQLException {
        List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
        templateInfoList.add(new ServiceTemplate());
        templateInfoList.add(new ServiceImplTemplate());
        templateInfoList.add(new JsTemplate());
        templateInfoList.add(new ViewTemplate());
        this.generate(generateInfo, templateInfoList);
    }

    public void generate(GenerateInfo generateInfo,List<TemplateInfo> templateInfos) throws IOException, SQLException {
        this.generate(new MysqlDataMeta(),generateInfo, templateInfos);
    }

    public void generate(DbMata dbMata,GenerateInfo generateInfo,List<TemplateInfo> templateInfos) throws SQLException, IOException {
        List<TableInfo> tableInfos= dbMata.getTableInfo(generateInfo.getTablePattern());
        TableInfo tableInfoTmp = null;
        TemplateInfo templateInfoTmp = null;
        String projectPath = generateInfo.getProjectUrl();
        String packagePath = generateInfo.getPackageUrl().replaceAll("\\.","/");
        String modelName = generateInfo.getModelName();
        String  filePath = "";
        for (int i = 0,size = tableInfos.size(); i < size; i++) {
            tableInfoTmp = tableInfos.get(i);
            String className = tableInfoTmp.getClassName();
            Map<String,Object> root = new HashMap<String,Object>();
            root.put("package",generateInfo.getPackageUrl());
            root.put("model",generateInfo.getModelName());
            root.put("tableInfo",tableInfoTmp);
            for (int j = 0,templateSize = templateInfos.size(); j < templateSize; j++) {
                templateInfoTmp = templateInfos.get(j);
                templateInfoTmp.setClassName(className);
                switch (templateInfoTmp.getIsBrowserOrServer()) {
                    case SERVER:
                        filePath = projectPath+"/"+templateInfoTmp.getServerUrl()+"/"+packagePath+"/"+modelName+"/"+templateInfoTmp.getFunctionPath()+"/"+templateInfoTmp.getFileName();
                        break;
                    case BROWSER:
                        filePath = projectPath+"/"+templateInfoTmp.getBrowserUrl()+"/"+templateInfoTmp.getPrefix()+"/"+templateInfoTmp.getFunctionPath()+"/"+modelName+"/"+templateInfoTmp.getFileName();
                        break;
                }
                saveSourceFile(filePath,root,templateInfoTmp.getTemplateName());
            }
        }


    }

    /**
     *
     * 获取模板文件
     *
     * @return
     */
    public GroupTemplate getGroupTemplate() {
        try {
            ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader("codeGenerator/template/");
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate groupTemplate = new GroupTemplate(classpathResourceLoader, cfg);
            groupTemplate.registerFunction("firstUpCase", new StringToUpperCase());
            groupTemplate.registerFunction("firstLowCase", new StringToLowerCase());
            return groupTemplate;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void saveSourceFile(String filePath, Map<String,Object> root,String templateName) throws IOException {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            File file = new File(filePath.substring(0,filePath.lastIndexOf("/")+1));
            file.mkdirs();
            out = new FileWriter(new File(filePath));
            Template temp = this.getGroupTemplate().getTemplate(templateName);
            temp.binding(root);
            String render = temp.render();
            out.write(render);
            out.close();
            System.out.println(render);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
