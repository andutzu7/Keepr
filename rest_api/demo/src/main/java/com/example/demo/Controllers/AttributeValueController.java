package com.example.demo.Controllers;

import com.example.demo.Assemblers.AttributeValueModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.AttributeValue;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.AttributeValueNotFoundException;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributeValuesRepository;
import com.example.demo.Repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Services.AttributeValueService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class AttributeValueController {

    @Autowired
    AttributeValueService attributeValueService;


    @GetMapping("/attribute_values")
    public CollectionModel<EntityModel<AttributeValue>> all() {
        return attributeValueService.all();
    }

    @PostMapping("/attribute_values")
    AttributeValue insertNewAttributeValue(@RequestBody AttributeValue newAttributeValue) {
        return attributeValueService.insertNewAttributeValue(newAttributeValue);
    }


    @PutMapping("/attribute_values/{id}")
    ResponseEntity<?> replaceItem(@RequestBody AttributeValue newAttributeValue, @PathVariable Integer id) {
        return attributeValueService.replaceAttributeValue(newAttributeValue,id);
    }
    @GetMapping("/attribute_values/{id}")
    public EntityModel<AttributeValue> one(@PathVariable Integer id) {
        return attributeValueService.one(id);
    }
    @DeleteMapping("/attribute_values/{id}")
    void deleteAttributeValue(@PathVariable Integer id) {
        attributeValueService.deleteAttributeValue(id);
    }
}