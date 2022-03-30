package com.example.demo.Repositories;

import com.example.demo.Entities.AttributeValue;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttributeValuesRepository extends JpaRepository<AttributeValue, Integer> {

}
