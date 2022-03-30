package com.example.demo.Repositories;

import com.example.demo.Entities.ItemAttributeValue;
import com.example.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemAttributeValuesRepository extends JpaRepository<ItemAttributeValue, Integer> {

}
