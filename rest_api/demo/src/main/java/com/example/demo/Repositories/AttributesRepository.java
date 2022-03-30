package com.example.demo.Repositories;

import com.example.demo.Entities.Attribute;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttributesRepository extends JpaRepository<Attribute, Integer> {

}
