package com.food.order.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facility")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    private String name;
    private String description;
    private String icon;
}
