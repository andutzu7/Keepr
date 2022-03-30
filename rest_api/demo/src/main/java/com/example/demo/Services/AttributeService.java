package com.example.demo.Services;

import com.example.demo.Assemblers.AttributeModelAssembler;
import com.example.demo.Controllers.AttributeController;
import com.example.demo.Entities.Attribute;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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


    public EntityModel<Attribute> one(@PathVariable Integer id) {

        Attribute attribute = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(attribute);
    }


    public void deleteAttribute(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
