package com.example.demo.Controllers;

import com.example.demo.Assemblers.ItemAttributeValueModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.ItemAttributeValue;
import com.example.demo.Exceptions.ItemAttributeValueNotFoundException;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.ItemAttributeValuesRepository;
import com.example.demo.Repositories.ItemsRepository;
import com.example.demo.Services.ItemAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public
class ItemAttributeValueController {

    @Autowired
    ItemAttributeValueService itemAttributeValueService;


    @GetMapping("/item_attribute_values")
    public CollectionModel<EntityModel<ItemAttributeValue>> all() {
        return itemAttributeValueService.all();
    }

    @PostMapping("/item_attribute_values")
    ItemAttributeValue insertNewItemAttributeValue(@RequestBody ItemAttributeValue newItemAttributeValue) {
        return itemAttributeValueService.insertNewItemAttributeValue(newItemAttributeValue);
    }

    // Single ItemAttributeValue

    @GetMapping("/item_attribute_values/{id}")
    public EntityModel<ItemAttributeValue> one(@PathVariable Integer id) {

        return itemAttributeValueService.one(id);
    }

    @PutMapping("/items_attribute_values/{id}")
    ResponseEntity<?> replaceItemAttributeValues(@RequestBody ItemAttributeValue newItemAttributeValue, @PathVariable Integer id) {
        return itemAttributeValueService.replaceItemAttributeValue(newItemAttributeValue,id);
    }
    @DeleteMapping("/items_attribute_values/{id}")
    void deleteItemAttributeValue(@PathVariable Integer id) {
        itemAttributeValueService.deleteItemAttributeValue(id);
    }
}