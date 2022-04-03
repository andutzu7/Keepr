package com.example.demo.Services;

import com.example.demo.Assemblers.AttributeModelAssembler;
import com.example.demo.Assemblers.AttributeValueModelAssembler;
import com.example.demo.Controllers.AttributeValueController;
import com.example.demo.Entities.AttributeValue;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.AttributeValueNotFoundException;
import com.example.demo.Repositories.AttributeValuesRepository;
import com.example.demo.Repositories.AttributesRepository;
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
public class AttributeValueService {
    @Autowired
    private final AttributeValuesRepository repository;

    private final AttributeValueModelAssembler assembler;


    AttributeValueService(AttributeValuesRepository repository, AttributeValueModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    public CollectionModel<EntityModel<AttributeValue>> all() {
        List<EntityModel<AttributeValue>> attributeValue = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(attributeValue, linkTo(methodOn(AttributeValueController.class).all()).withSelfRel());
    }

    public AttributeValue insertNewAttributeValue(@RequestBody AttributeValue newAttributeValue) {
        return repository.save(newAttributeValue);
    }

    public ResponseEntity<?> replaceAttributeValue(@RequestBody AttributeValue newAttributeValue, @PathVariable Integer id) {

        AttributeValue updatedAttributeValue = repository.findById(id) //
                .map(attributeValue -> {
                    attributeValue.setAttribute_id(newAttributeValue.getAttribute_id());
                    attributeValue.setValue(attributeValue.getValue());
                    return repository.save(attributeValue);
                }) //
                .orElseGet(() -> {
                    newAttributeValue.setId(id);
                    return repository.save(newAttributeValue);
                });

        EntityModel<AttributeValue> entityModel = assembler.toModel(updatedAttributeValue);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
// Single AttributeValue

    public EntityModel<AttributeValue> one(@PathVariable Integer id) {

        AttributeValue attributeValue = repository.findById(id) //
                .orElseThrow(() -> new AttributeValueNotFoundException(id));
        return assembler.toModel(attributeValue);
    }

    public void deleteAttributeValue(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
