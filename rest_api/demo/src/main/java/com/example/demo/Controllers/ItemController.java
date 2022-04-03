package com.example.demo.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.ItemsRepository;
import com.example.demo.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {
        return itemService.all();
    }

    @PostMapping("/items")
    Item insertNewItem(@RequestBody Item newItem) {
        return itemService.insertNewItem(newItem);
    }

    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Integer id) {

        return itemService.one(id);
    }

    @PutMapping("/items/{id}")
    ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Integer id) {
        return itemService.replaceItem(newItem,id);
    }

    @DeleteMapping("/items/{id}")
    void deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }
}