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
        generateInfo.setPackageUrl("com.ranran.uums");
        generateInfo.setModelName("system");
        generateInfo.setTablePattern("ts_district%");
        List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
//        templateInfoList.add(new HControllerImplTemplate());
//        templateInfoList.add(new HControllerLocalTemplate());
//        templateInfoList.add(new HControllerTemplate());
//        templateInfoList.add(new HMapperTemplate());
        templateInfoList.add(new HMapperXmlTemplate());
        templateInfoList.add(new HModelTemplate());
//        templateInfoList.add(new HServiceImplTemplate());
//        templateInfoList.add(new HServiceTemplate());
//        templateInfoList.add(new HVoDeleteTemplate());
//        templateInfoList.add(new HVoExportTemplate());
//        templateInfoList.add(new HVoImportTemplate());
//        templateInfoList.add(new HVoSelectTemplate());
//        templateInfoList.add(new HVoUpdateTemplate());
//        templateInfoList.add(new QHtmlTemplate());
//        templateInfoList.add(new QJsTemplate());
        Coder coder = new Coder();
        coder.generate(generateInfo,templateInfoList);
//        整个库生成
//        List<String> ids = new ArrayList<String>();
//        ids.add("a");
//        ids.add("a");
//        System.out.printf(ids.toString().substring(1,ids.toString().length()-1));
//        SpringApplication.run(App.class, args);
    }
}
