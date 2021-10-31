package com.automation.framework.data.supplier;

import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;

public class DepartmentData {

    @Value("${resources.dir.path}/DepartmentTestData.xlsx")
    private Path departmentTestData;



    @Test
    public void csvResourceTest() throws IOException {
        String resourceName = "DepartmentTestData.xlsx";
        //Path path = reader.getFilePathByFilename(resourceName);
        //System.out.println(path);

    }





}
