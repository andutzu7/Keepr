package com.example.demo.Controllers;

import com.example.demo.Assemblers.AttributeValueModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.AttributeValue;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.AttributeValueNotFoundException;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributeValuesRepository;
import com.example.demo.Repositories.ItemsRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class AttributeValueController {

    private final AttributeValuesRepository repository;

    private final AttributeValueModelAssembler assembler;

   AttributeValueController(AttributeValuesRepository repository, AttributeValueModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }



    @GetMapping("/attribute_values")
    public CollectionModel<EntityModel<AttributeValue>> all() {
        List<EntityModel<AttributeValue>> attributeValue = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(attributeValue, linkTo(methodOn(AttributeValueController.class).all()).withSelfRel());
    }

    @PostMapping("/attribute_values")
    AttributeValue insertNewAttributeValue(@RequestBody AttributeValue newAttributeValue) {
        return repository.save(newAttributeValue);
    }

    // Single AttributeValue

    @GetMapping("/attribute_values/{id}")
    public EntityModel<AttributeValue> one(@PathVariable Integer id) {

        AttributeValue attributeValue= repository.findById(id) //
                .orElseThrow(() -> new AttributeValueNotFoundException(id));
        return assembler.toModel(attributeValue);
    }
    @DeleteMapping("/attribute_values/{id}")
    void deleteAttributeValue(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}