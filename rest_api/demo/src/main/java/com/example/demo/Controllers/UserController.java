package com.example.demo.Controllers;

import com.example.demo.Assemblers.ItemModelAssembler;
import com.example.demo.Assemblers.UserModelAssembler;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.User;
import com.example.demo.Exceptions.ItemNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.ItemsRepository;
import com.example.demo.Repositories.UsersRepository;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public
class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        return userService.all();
    }

    @PostMapping("/users")
    User insertNewUser(@RequestBody User newUser) {
        return userService.insertNewUser(newUser);
    }

    // Single User

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Integer id) {

        return userService.one(id);
    }

    // NO PUT IMPLEMENTED

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}