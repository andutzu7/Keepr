package com.example.demo.Assemblers;

import com.example.demo.Controllers.AttributeController;
import com.example.demo.Controllers.ItemController;
import com.example.demo.Entities.Attribute;
import com.example.demo.Entities.Item;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AttributeModelAssembler implements RepresentationModelAssembler<Attribute, EntityModel<Attribute>> {

    @Override
    public EntityModel<Attribute> toModel(Attribute attribute) {

        return EntityModel.of(attribute, //
                linkTo(methodOn(AttributeController.class).one(attribute.getId())).withSelfRel(),
                linkTo(methodOn(AttributeController.class).all()).withRel("attributes"));
    }
}