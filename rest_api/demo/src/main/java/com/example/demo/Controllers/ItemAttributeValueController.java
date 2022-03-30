package com.example.demo.Controllers;

import com.example.demo.Assemblers.ItemAttributeValueModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.ItemAttributeValue;
import com.example.demo.Exceptions.ItemAttributeValueNotFoundException;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.ItemAttributeValuesRepository;
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
class ItemAttributeValueController {

    private final ItemAttributeValuesRepository repository;

    private final ItemAttributeValueModelAssembler assembler;

   ItemAttributeValueController(ItemAttributeValuesRepository repository, ItemAttributeValueModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }



    @GetMapping("/item_attribute_values")
    public CollectionModel<EntityModel<ItemAttributeValue>> all() {
        List<EntityModel<ItemAttributeValue>> itemAttributeValue = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(itemAttributeValue, linkTo(methodOn(ItemAttributeValueController.class).all()).withSelfRel());
    }

    @PostMapping("/item_attribute_values")
    ItemAttributeValue insertNewItemAttributeValue(@RequestBody ItemAttributeValue newItemAttributeValue) {
        return repository.save(newItemAttributeValue);
    }

    // Single ItemAttributeValue

    @GetMapping("/item_attribute_values/{id}")
    public EntityModel<ItemAttributeValue> one(@PathVariable Integer id) {

        ItemAttributeValue itemAttributeValue = repository.findById(id) //
                .orElseThrow(() -> new ItemAttributeValueNotFoundException(id));
        return assembler.toModel(itemAttributeValue);
    }

    @DeleteMapping("/items_attribute_values/{id}")
    void deleteItemAttributeValue(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}