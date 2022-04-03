package com.example.demo.Services;

import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Controllers.ItemController;
import com.example.demo.Entities.Item;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Repositories.ItemsRepository;
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
public class ItemService{
    @Autowired
    private final ItemsRepository repository;

    private final ItemModelAssembler assembler;


    public ItemService(ItemsRepository repository, ItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    public CollectionModel<EntityModel<Item>> all() {
        List<EntityModel<Item>> item = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(item, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }

    public Item insertNewItem(@RequestBody Item newItem) {
        return repository.save(newItem);
    }

    // Single item

    public EntityModel<Item> one(@PathVariable Integer id) {

        Item item = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));
        return assembler.toModel(item);
    }

    public ResponseEntity<?> replaceItem(@RequestBody Item newItem, @PathVariable Integer id) {

        Item updatedItem = repository.findById(id) //
                .map(item -> {
                    item.setUser_id(newItem.getUser_id());
                    item.setTitle(newItem.getTitle());
                    item.setDescription(newItem.getDescription());
                    item.setDue_date(newItem.getDue_date());
                    return repository.save(item);
                }) //
                .orElseGet(() -> {
                    newItem.setId(id);
                    return repository.save(newItem);
                });

        EntityModel<Item> entityModel = assembler.toModel(updatedItem);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public void deleteItem(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
