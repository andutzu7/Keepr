package com.example.demo.Services;

import com.example.demo.Assemblers.UserModelAssembler;
import com.example.demo.Controllers.UserController;
import com.example.demo.Entities.User;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Repositories.UsersRepository;
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
public class UserService{
    @Autowired
    private final UsersRepository repository;

    private final UserModelAssembler assembler;

    public UserService(UsersRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(user, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    public User insertNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single User

    public EntityModel<User> one(@PathVariable Integer id) {

        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }


    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
