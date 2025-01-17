package com.food.order.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testimony")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Testimony {
    private String author;
    private String description;
    private String imageUrl;
}
