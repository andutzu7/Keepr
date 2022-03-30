package com.example.demo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="attribute_values")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer attribute_id;

    private String value;

    public AttributeValue() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(Integer attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AttributeValue(Integer id, Integer attribute_id, String value) {
        this.id = id;
        this.attribute_id = attribute_id;
        this.value = value;
    }

    public AttributeValue(Integer id, Integer attribute_id) {
        this.id = id;
        this.attribute_id = attribute_id;
    }

    public AttributeValue(Integer id) {
        this.id = id;
    }
}
