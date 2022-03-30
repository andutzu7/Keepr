package com.example.demo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="item_attribute_values")
public class ItemAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer item_id;

    private Integer attribute_value_id;

    public ItemAttributeValue() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getAttribute_value_id() {
        return attribute_value_id;
    }

    public void setAttribute_value_id(Integer attribute_value_id) {
        this.attribute_value_id = attribute_value_id;
    }

    public ItemAttributeValue(Integer id, Integer item_id, Integer attribute_value_id) {
        this.id = id;
        this.item_id = item_id;
        this.attribute_value_id = attribute_value_id;
    }

    public ItemAttributeValue(Integer id, Integer item_id) {
        this.id = id;
        this.item_id = item_id;
    }

    public ItemAttributeValue(Integer id) {
        this.id = id;
    }

}

