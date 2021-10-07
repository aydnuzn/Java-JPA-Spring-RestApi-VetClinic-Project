package com.works.models.elasticsearch;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;


@Document(indexName = "customer")
@Data
public class Customer {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String surname;

    @Field(type = FieldType.Text)
    private String tel1;

    @Field(type = FieldType.Text)
    private String mail;

    @Field(type = FieldType.Text)
    private String tcnumber;

    @Field(type = FieldType.Text)
    private String city;

    @Field(type = FieldType.Text)
    private String district;

    @Field(type = FieldType.Text)
    private String group;

}