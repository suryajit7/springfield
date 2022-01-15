package com.automation.framework.data.entity.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Root
@Jacksonized
public class Catalog {

    @ElementList
    private List<Book> book;

    @Attribute(required = false)
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
