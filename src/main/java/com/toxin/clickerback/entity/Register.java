package com.toxin.clickerback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "registers")
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @Id
    private String id;

    @DBRef
    @Indexed(unique = true)
    private User from;

    @DBRef
    private User to;

    public Register(User from, User to) {
        this.from = from;
        this.to = to;
    }
}
