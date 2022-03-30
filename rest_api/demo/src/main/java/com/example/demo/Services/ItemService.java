package com.example.demo.Services;

import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Controllers.ItemController;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class ItemService{
    @Autowired
    private final ItemsRepository repository;

    private final ItemModelAssembler assembler;


    public ItemService(ItemsRepository repository, ItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {
        List<EntityModel<Item>> item = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(item, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    @PostMapping("/items")
    public Item insertNewItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    // Single item

    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Integer id) {

        Item item = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

//    @PutMapping("/item/{id}")
//    Item replaceItem(@RequestBody Item newItem, @PathVariable Integer id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newItem.getName());
//                    employee.setRole(newItem.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
