package com.automation.framework.gui.module.tdm;

import com.automation.framework.gui.BaseTestNGTest;
import com.automation.framework.data.entity.app.ems.Department;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;


public class ReaderTest extends BaseTestNGTest {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${user.dir}/src/test/resources/datasets/test-data-file.csv")
    private Resource csvResource;

    @Value("https://www.w3.org/TR/PNG/iso_8859-1.txt")
    private Resource textFile;

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

    @Test(priority = 1)
    public void verifyValueInjection() throws IOException {
        Files.readAllLines(csvResource.getFile().toPath())
                .forEach(System.out::println);

        System.out.print(new String(textFile.getInputStream().readAllBytes()));
    }

    @Test(priority = 2)
    public void verifyTestPropertiesFile() throws IOException {

        Properties property = PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("property-test.properties"));
        System.out.print(String.valueOf(property));
    }


}