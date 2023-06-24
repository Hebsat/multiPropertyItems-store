package com.mystore.fruitstore.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.fruitstore.api.ItemRequest;
import com.mystore.fruitstore.api.ItemResponse;
import com.mystore.fruitstore.api.PropertyRequest;
import com.mystore.fruitstore.api.Response;
import com.mystore.fruitstore.mappers.ItemsMapper;
import com.mystore.fruitstore.model.*;
import com.mystore.fruitstore.repositories.ItemsRepository;
import com.mystore.fruitstore.repositories.PropertiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final PropertiesRepository propertiesRepository;
    private final ItemsMapper itemsMapper;

    public Response<List<ItemResponse>> getAllItems() {
        List<ItemResponse> itemRespons = itemsRepository.findAll().stream()
                .map(itemsMapper::getItemResponseFromItem).toList();
        return Response.<List<ItemResponse>>builder()
                .count(itemRespons.size())
                .data(itemRespons)
                .build();
    }

    public Response<ItemResponse> createItem(ItemRequest itemRequest) {
        Item item = getAndSaveItemFromRequest(itemRequest);
        ItemResponse itemResponse = itemsMapper.getItemResponseFromItem(item);
        return Response.<ItemResponse>builder()
                .data(itemResponse)
                .build();
    }

    private Item getAndSaveItemFromRequest(ItemRequest itemRequest) {
        Item item = new Item();
        item.setItemName(itemRequest.getName());
        item.setDescription(itemRequest.getDescription());
        itemsRepository.save(item);
        item.setProperties(getProperties(itemRequest.getProperties(), item));
        return item;
    }

    private List<AbstractProperty> getProperties(List<PropertyRequest<?>> propertyRequests, Item item) {
        if (propertyRequests == null) return Collections.emptyList();
        List<AbstractProperty> properties = new ArrayList<>();
        propertyRequests.forEach(propertyRequest -> {
            if (propertyRequest.getValue() instanceof Boolean) {
                BooleanProperty booleanProperty = new BooleanProperty();
                booleanProperty.setItem(item);
                booleanProperty.setPropertyName(propertyRequest.getName());
                booleanProperty.setBooleanValue((Boolean) propertyRequest.getValue());
                propertiesRepository.save(booleanProperty);
                properties.add(booleanProperty);
            }
            else if (propertyRequest.getValue() instanceof Integer) {
                IntegerProperty integerProperty = new IntegerProperty();
                integerProperty.setItem(item);
                integerProperty.setPropertyName(propertyRequest.getName());
                integerProperty.setIntegerValue((Integer) propertyRequest.getValue());
                propertiesRepository.save(integerProperty);
                properties.add(integerProperty);
            }
            else if (propertyRequest.getValue() instanceof Double) {
                DoubleProperty doubleProperty = new DoubleProperty();
                doubleProperty.setItem(item);
                doubleProperty.setPropertyName(propertyRequest.getName());
                doubleProperty.setDoubleValue((Double) propertyRequest.getValue());
                propertiesRepository.save(doubleProperty);
                properties.add(doubleProperty);
            }
            else if (propertyRequest.getValue() instanceof String) {
                StringProperty stringProperty = new StringProperty();
                stringProperty.setItem(item);
                stringProperty.setPropertyName(propertyRequest.getName());
                stringProperty.setStringValue((String) propertyRequest.getValue());
                propertiesRepository.save(stringProperty);
                properties.add(stringProperty);
            }
            else {
                ObjectMapper objectMapper = new ObjectMapper();
                ItemRequest itemRequest = objectMapper.convertValue(propertyRequest.getValue(), ItemRequest.class);
                Item embeddedItem = getAndSaveItemFromRequest(itemRequest);
                ObjectProperty objectProperty = new ObjectProperty();
                objectProperty.setItem(item);
                objectProperty.setPropertyName(propertyRequest.getName());
                objectProperty.setItemValue(embeddedItem);
                propertiesRepository.save(objectProperty);
                properties.add(objectProperty);
            }
        });
        return properties;
    }
}
