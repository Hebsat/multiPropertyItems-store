package com.mystore.fruitstore.mappers;

import com.mystore.fruitstore.api.ItemResponse;
import com.mystore.fruitstore.api.PropertyResponse;
import com.mystore.fruitstore.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemsMapper {

    public ItemResponse getItemResponseFromItem(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getItemName())
                .description(item.getDescription())
                .properties(getProperties(item))
                .build();
    }

    private List<PropertyResponse<?>> getProperties(Item item) {
        if (item.getProperties() == null) {
            return Collections.emptyList();
        }
        List<PropertyResponse<?>> propertyResponses = new ArrayList<>();
        item.getProperties().forEach(property -> {
            if (property instanceof BooleanProperty booleanProperty) {
                propertyResponses.add(getBooleanProperty(booleanProperty));
            }
            if (property instanceof IntegerProperty integerProperty) {
                propertyResponses.add(getIntegerProperty(integerProperty));
            }
            if (property instanceof DoubleProperty doubleProperty) {
                propertyResponses.add(getDoubleProperty(doubleProperty));
            }
            if (property instanceof StringProperty stringProperty) {
                propertyResponses.add(getStringProperty(stringProperty));
            }
            if (property instanceof ObjectProperty objectProperty) {
                propertyResponses.add(getObjectProperty(objectProperty));
            }
        });
        return propertyResponses.isEmpty() ? null : propertyResponses;
    }

    private PropertyResponse<String> getStringProperty(StringProperty stringProperty) {
        return PropertyResponse.<String>builder()
                .id(stringProperty.getId())
                .name(stringProperty.getPropertyName())
                .value(stringProperty.getStringValue())
                .build();
    }

    private PropertyResponse<Integer> getIntegerProperty(IntegerProperty integerProperty) {
        return PropertyResponse.<Integer>builder()
                .id(integerProperty.getId())
                .name(integerProperty.getPropertyName())
                .value(integerProperty.getIntegerValue())
                .build();
    }

    private PropertyResponse<Double> getDoubleProperty(DoubleProperty doubleProperty) {
        return PropertyResponse.<Double>builder()
                .id(doubleProperty.getId())
                .name(doubleProperty.getPropertyName())
                .value(doubleProperty.getDoubleValue())
                .build();
    }

    private PropertyResponse<Boolean> getBooleanProperty(BooleanProperty booleanProperty) {
        return PropertyResponse.<Boolean>builder()
                .id(booleanProperty.getId())
                .name(booleanProperty.getPropertyName())
                .value(booleanProperty.getBooleanValue())
                .build();
    }
    private PropertyResponse<ItemResponse> getObjectProperty(ObjectProperty objectProperty) {
        return PropertyResponse.<ItemResponse>builder()
                .id(objectProperty.getId())
                .name(objectProperty.getPropertyName())
                .value(getItemResponseFromItem(objectProperty.getItemValue()))
                .build();
    }
}
