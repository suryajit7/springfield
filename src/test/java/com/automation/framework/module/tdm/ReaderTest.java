package com.automation.framework.module.tdm;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.data.entity.app.ems.Department;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;


public class ReaderTest extends BaseTestNGTest {

    @Autowired
    private FileReader fileReader;

    @Test(priority = 0)
    public void verifyFileReaderUtility() throws Exception {

        fileReader.readTxtFile("UserTestData.txt")
                .forEach(System.out::println);

        fileReader.readCsvFile("EmployeeData.csv", Employee.class)
                .forEach(System.out::println);

        System.out.println(fileReader.readJsonFile("EMSTestData.json"));

        fileReader.readExcelFile("DepartmentTestData.xlsx", Department.class, "Sheet1")
                .forEach(System.out::println);


    }


}