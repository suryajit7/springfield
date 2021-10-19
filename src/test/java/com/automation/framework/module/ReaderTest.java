package com.automation.framework.module;

import com.automation.framework.BaseTestNGTest;
import com.automation.framework.data.FileReader;
import com.automation.framework.data.entity.app.ems.Employee;
import com.automation.framework.util.converter.CsvToJson;
import com.automation.framework.util.converter.CsvToString;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class ReaderTest extends BaseTestNGTest {

    @Value("${test-resources.dir.path}")
    private static Path testDir;

    @BeforeClass
    public void setup() {
    }

    @Test(priority = 0)
    public void verifyDashboard() throws IOException {
        String resourceName = "property-test.properties";
        FileReader reader = new FileReader();

        Path path = reader.getFilePathForFile(resourceName);
        reader.readFile(resourceName);
        System.out.println(path);


        CsvToJson csvToJson = new CsvToJson();
        CsvToString csv = new CsvToString();

        List<Employee> employeeList = csv.csvToString(reader.getFilePathForFile("EmployeeData.csv"), Employee.class);
        System.out.println(employeeList);


    }


}