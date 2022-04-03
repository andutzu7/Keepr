package com.example.demo.Services;

import com.example.demo.Assemblers.ItemAttributeValueModelAssembler;
import com.example.demo.Controllers.ItemAttributeValueController;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.ItemAttributeValue;
import com.example.demo.Exceptions.ItemAttributeValueNotFoundException;
import com.example.demo.Repositories.ItemAttributeValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ItemAttributeValueService{
    @Autowired
    private final ItemAttributeValuesRepository repository;

    private final ItemAttributeValueModelAssembler assembler;


    public ItemAttributeValueService(ItemAttributeValuesRepository repository, ItemAttributeValueModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    public CollectionModel<EntityModel<ItemAttributeValue>> all() {
        List<EntityModel<ItemAttributeValue>> itemAttributeValue = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(itemAttributeValue, linkTo(methodOn(ItemAttributeValueController.class).all()).withSelfRel());
    }

    public ItemAttributeValue insertNewItemAttributeValue(@RequestBody ItemAttributeValue newItemAttributeValue) {
        return repository.save(newItemAttributeValue);
    }

    // Single ItemAttributeValue

    public EntityModel<ItemAttributeValue> one(@PathVariable Integer id) {

        ItemAttributeValue itemAttributeValue = repository.findById(id) //
                .orElseThrow(() -> new ItemAttributeValueNotFoundException(id));
        return assembler.toModel(itemAttributeValue);
    }

    public ResponseEntity<?> replaceItemAttributeValue(@RequestBody ItemAttributeValue newItemAttributeValue, @PathVariable Integer id) {

        ItemAttributeValue updatedItemAttributeValue = repository.findById(id) //
                .map(itemAttributeValue -> {
                    itemAttributeValue.setItem_id(newItemAttributeValue.getItem_id());
                    itemAttributeValue.setAttribute_value_id(newItemAttributeValue.getAttribute_value_id());
                    return repository.save(itemAttributeValue);
                }) //
                .orElseGet(() -> {
                    newItemAttributeValue.setId(id);
                    return repository.save(newItemAttributeValue);
                });

        EntityModel<ItemAttributeValue> entityModel = assembler.toModel(updatedItemAttributeValue);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    public void deleteItemAttributeValue(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
