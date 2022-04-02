package com.example.demo.Entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;

    private String title;

    private String description;

    private LocalDateTime due_date;

    public Item() {
    }

    public Item(Integer user_id) {
        this.user_id = user_id;
    }

    public Item(Integer user_id, String title, String description, LocalDateTime due_date) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
    }

    public Item(Integer user_id, String title, String description) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
    }

    public Item(Integer user_id, String title) {
        this.user_id = user_id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }
}
