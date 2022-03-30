package com.example.demo.Assemblers;

import com.example.demo.Controllers.ItemController;
import com.example.demo.Entities.Item;
import com.example.demo.Entities.ItemAttributeValue;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemAttributeValueModelAssembler implements RepresentationModelAssembler<ItemAttributeValue, EntityModel<ItemAttributeValue>> {

    @Override
    public EntityModel<ItemAttributeValue> toModel(ItemAttributeValue itemAttributeValue) {

        return EntityModel.of(itemAttributeValue, //
                linkTo(methodOn(ItemController.class).one(itemAttributeValue.getId())).withSelfRel(),
                linkTo(methodOn(ItemController.class).all()).withRel("item_attribute_value"));
    }
}