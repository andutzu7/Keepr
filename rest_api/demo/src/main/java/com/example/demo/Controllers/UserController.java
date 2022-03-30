package com.example.demo.Controllers;

import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Assemblers.UserModelAssembler;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.User;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.ItemsRepository;
import com.example.demo.Repositories.UsersRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class UserController {

    private final UsersRepository repository;

    private final UserModelAssembler assembler;

   UserController(UsersRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }



    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(user, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    User insertNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single User

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Integer id) {

        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }


    @DeleteMapping("/users/{id}")
    void deleteUsers(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}