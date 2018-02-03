package com.ranran.study.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PersonOptData {
    @RequestMapping("/downloadExcel.html")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户数据表","UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        List<Person> list = new ArrayList<Person>();
        Person person = new Person("z","男_1",new Date());
        list.add(person);
        person = new Person("z1","男_1",new Date());
        list.add(person);
        person = new Person("z1","男_1",new Date());
        list.add(person);
        person = new Person("z1","男_1",new Date());
        list.add(person);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Person.class, list);
        workbook.write(response.getOutputStream());
    }

}
