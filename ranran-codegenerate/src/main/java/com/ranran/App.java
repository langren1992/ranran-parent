package com.ranran;

import com.ranran.codegenerate.Coder;
import com.ranran.codegenerate.GenerateInfo;
import com.ranran.codegenerate.template.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    /**
     *
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main( String[] args ) throws SQLException, IOException {
        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setProjectUrl("E:/workspace/server/ranran-parent/ranran-config-client");
        generateInfo.setPackageUrl("com.ranran.uums.operate");
        generateInfo.setModelName("system");
        generateInfo.setTablePattern("ts_role");
        List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
        templateInfoList.add(new ServiceTemplate());
        templateInfoList.add(new ServiceImplTemplate());
        templateInfoList.add(new ServiceBaseTemplate());
        templateInfoList.add(new JsTemplate());
        templateInfoList.add(new ViewTemplate());
        templateInfoList.add(new MapperTemplate());
        templateInfoList.add(new MdTemplate());
        templateInfoList.add(new ModelTemplate());
        Coder coder = new Coder();
        coder.generate(generateInfo,templateInfoList,"TsRoleUpdateEnable");
//        整个库生成
//        List<String> ids = new ArrayList<String>();
//        ids.add("a");
//        ids.add("a");
//        System.out.printf(ids.toString().substring(1,ids.toString().length()-1));
//        SpringApplication.run(App.class, args);
    }
}