package com.ist.datalog.business.model;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Data
@Document(collection = "models")
public class Model {

    @Id
    private String id;
    private String name;
    private String directory;
    private String detail;
    private Binary image;
    private String time;

    // Getters and Setters
    // ...
}
