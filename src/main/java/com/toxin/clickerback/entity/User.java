package com.toxin.clickerback.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Field(value = "name")
    private String name;

    @Indexed(unique = true)
    private UUID token;

}
