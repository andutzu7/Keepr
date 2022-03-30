package com.example.demo.Controllers;

import com.example.demo.Assemblers.AttributeModelAssembler;
import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Entities.Attribute;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.AttributesRepository;
import com.example.demo.Repositories.ItemsRepository;
import com.example.demo.Services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AttributeService attributeService;

    @GetMapping("/attributes")
    public CollectionModel<EntityModel<Attribute>> all() {

        return attributeService.all();
    }

    @PostMapping("/attributes")
    Attribute insertNewAttribute(@RequestBody Attribute newAttribute) {
        return attributeService.insertNewAttribute(newAttribute);
    }

    // Single item

    @GetMapping("/attributes/{id}")
    public EntityModel<Attribute> one(@PathVariable Integer id) {
        return attributeService.one(id);
    }


    @DeleteMapping("/attributes/{id}")
    void deleteAttribute(@PathVariable Integer id) {
        attributeService.deleteAttribute(id);
    }
}