package com.example.demo.Assemblers;

import com.example.demo.Controllers.AttributeValueController;
import com.example.demo.Controllers.ItemController;
import com.example.demo.Entities.AttributeValue;
import com.example.demo.Entities.Item;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AttributeValueModelAssembler implements RepresentationModelAssembler<AttributeValue, EntityModel<AttributeValue>> {

    @Override
    public EntityModel<AttributeValue> toModel(AttributeValue attributeValue) {

        return EntityModel.of(attributeValue, //
                linkTo(methodOn(AttributeValueController.class).one(attributeValue.getId())).withSelfRel(),
                linkTo(methodOn(AttributeValueController.class).all()).withRel("attribute_values"));
    }
}