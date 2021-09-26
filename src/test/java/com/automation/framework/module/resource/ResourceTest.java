package com.automation.framework.module.resource;

import com.automation.framework.BaseTestNGTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;

public class ResourceTest extends BaseTestNGTest {

    @Value("classpath:datasets/datasets-file.csv")
    private Resource csvResource;

    @Value("https://www.w3.org/TR/PNG/iso_8859-1.txt")
    private Resource textFile;

    @Test
    public void csvResourceTest() throws IOException {
        Files.readAllLines(csvResource.getFile().toPath())
                .forEach(System.out::println);
    }

    @Test
    public void txtResourceTest() throws IOException {
        System.out.printf(new String(textFile.getInputStream().readAllBytes()));
    }
}
