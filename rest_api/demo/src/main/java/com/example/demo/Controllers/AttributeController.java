package com.example.demo.Controllers;

import com.example.demo.Assemblers.AttributeModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.Attribute;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributesRepository;
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
class AttributeController {

    private final AttributesRepository repository;

    private final AttributeModelAssembler assembler;

   AttributeController(AttributesRepository repository, AttributeModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }



    @GetMapping("/attributes")
    public CollectionModel<EntityModel<Attribute>> all() {
        List<EntityModel<Attribute>> attributes= repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(attributes, linkTo(methodOn(AttributeController.class).all()).withSelfRel());
    }

    @PostMapping("/attributes")
    Attribute insertNewAttribute(@RequestBody Attribute newAttribute) {
        return repository.save(newAttribute);
    }

    // Single item

    @GetMapping("/attributes/{id}")
    public EntityModel<Attribute> one(@PathVariable Integer id) {

        Attribute attribute = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(attribute);
    }


    @DeleteMapping("/attributes/{id}")
    void deleteItem(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}