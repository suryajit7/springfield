package com.automation.framework.data.supplier;

import com.automation.framework.data.FileReader;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;

public class DepartmentData {

    @Value("${resources.dir.path}/DepartmentTestData.xlsx")
    private Path departmentTestData;

    private FileReader reader = new FileReader();



    @Test
    public void csvResourceTest() throws IOException {
        String resourceName = "DepartmentTestData.xlsx";

        //Path path = reader.getFilePathByFilename(resourceName);
        //System.out.println(path);

    }

 /*   public Department getDepartmentData(){
        Reader.of(Department.class)
                .from()

        return
    }*/





}
