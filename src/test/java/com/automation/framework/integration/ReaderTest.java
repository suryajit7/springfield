package com.automation.framework.integration;

import com.automation.framework.SpringfieldApplicationTests;
import com.automation.framework.data.entity.ems.Employee;
import com.automation.framework.util.file.FileReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ReaderTest extends SpringfieldApplicationTests {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${user.dir}/src/main/resources/datasets/test-data-file.csv")
    private Path csvResource;

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
        Files.readAllLines(csvResource.toAbsolutePath())
                .forEach(System.out::println);

        System.out.print(new String(textFile.getInputStream().readAllBytes()));
    }


}