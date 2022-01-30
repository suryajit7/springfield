package com.automation.framework.integration;

import com.automation.framework.AutomationSuiteApplicationTests;
import com.automation.framework.data.entity.ems.Employee;
import com.automation.framework.util.file.FileReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;


public class ReaderTest extends AutomationSuiteApplicationTests {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${user.dir}/src/test/resources/datasets/test-data-file.csv")
    private Resource csvResource;

    @Value("https://www.w3.org/TR/PNG/iso_8859-1.txt")
    private Resource textFile;

    @Test
    public void verifyFileReaderUtility() throws Exception {

        fileReader.readTxtFile("UserTestData.txt")
                .forEach(System.out::println);

        fileReader.readCsvFile("EmployeeData.csv", Employee.class)
                .forEach(System.out::println);

        System.out.println(fileReader.readJsonFile("EMSTestData.json"));
    }

    @Test
    public void verifyValueInjection() throws IOException {
        Files.readAllLines(csvResource.getFile().toPath())
                .forEach(System.out::println);

        System.out.print(new String(textFile.getInputStream().readAllBytes()));
    }

    @Test
    public void verifyTestPropertiesFile() throws IOException {

        //Properties property = PropertiesLoaderUtils.loadProperties(resourceLoader.getResource("application.properties"));
        //System.out.print(property);
    }


}