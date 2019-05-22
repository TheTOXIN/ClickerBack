package com.toxin.clickerback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "counters")
@AllArgsConstructor
@NoArgsConstructor
public class Counter {

    @Id
    private String id;

    @DBRef
    @Indexed(unique = true)
    private User user;

    @Field
    private int count = 0;
}
