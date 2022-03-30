package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.Item;


public interface ItemsRepository extends JpaRepository<Item, Integer> {

}
