package com.example.demo.Services;

import com.example.demo.Assemblers.AttributeModelAssembler;
import com.example.demo.Controllers.AttributeController;
import com.example.demo.Entities.Attribute;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Friendship Service
 */
@Service
public class AttributeService {
    @Autowired
    private final AttributesRepository repository;

    private final AttributeModelAssembler assembler;


    public AttributeService(AttributesRepository repository, AttributeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public CollectionModel<EntityModel<Attribute>> all() {
        List<EntityModel<Attribute>> attributes= repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(attributes, linkTo(methodOn(AttributeController.class).all()).withSelfRel());
    }

    public Attribute insertNewAttribute(@RequestBody Attribute newAttribute) {
        return repository.save(newAttribute);
    }

    public ResponseEntity<?> replaceAttribute(@RequestBody Attribute newAttribute, @PathVariable Integer id) {

        Attribute updatedAttribute = repository.findById(id) //
                .map(attribute -> {
                    attribute.setUser_id(newAttribute.getUser_id());
                    attribute.setName(newAttribute.getName());
                    return repository.save(attribute);
                }) //
                .orElseGet(() -> {
                    newAttribute.setId(id);
                    return repository.save(newAttribute);
                });

        EntityModel<Attribute> entityModel = assembler.toModel(updatedAttribute);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public EntityModel<Attribute> one(@PathVariable Integer id) {

        Attribute attribute = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(attribute);
    }


    public void deleteAttribute(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
