package com.food.order.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "staff")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    private String Id;
    private String name;
    private String position;
    private String about;
    private String imageUrl;

}
