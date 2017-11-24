package com.ranran;

import com.ranran.codegenerate.Coder;
import com.ranran.codegenerate.GenerateInfo;
import com.ranran.codegenerate.template.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws SQLException, IOException {
        GenerateInfo generateInfo = new GenerateInfo();
        generateInfo.setProjectUrl("E:/workspace/server/ranran-parent/ranran-config-client");
        generateInfo.setPackageUrl("com.ranran.uums");
        generateInfo.setModelName("system");
        generateInfo.setTablePattern("ts_role");
        List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
        templateInfoList.add(new ServiceTemplate());
        templateInfoList.add(new ServiceImplTemplate());
        templateInfoList.add(new JsTemplate());
        templateInfoList.add(new ViewTemplate());
        templateInfoList.add(new DaoTemplate());
        templateInfoList.add(new MdTemplate());
        templateInfoList.add(new ModelTemplate());
        Coder coder = new Coder();
        coder.generate(generateInfo,templateInfoList);
        //整个库生成
    }
}
