package com.mystore.fruitstore.controllers;

import com.mystore.fruitstore.api.ItemRequest;
import com.mystore.fruitstore.api.ItemResponse;
import com.mystore.fruitstore.api.Response;
import com.mystore.fruitstore.services.ItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping
    public Response<List<ItemResponse>> getAllItems() {

        return itemsService.getAllItems();
    }

    @PostMapping
    public Response<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {

        return itemsService.createItem(itemRequest);
    }
}
