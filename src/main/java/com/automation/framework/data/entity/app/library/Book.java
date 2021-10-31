package com.automation.framework.data.entity.app.library;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Root
public class Book {

    @Attribute(name="id")
    private String id;

    @Element(name="author")
    private String author;
    @Element
    private String title;

    @Element
    private String genre;
    @Element
    private Double price;
    @Element
    private Date publish_date;
    @Element
    private String description;
}