package com.toxin.clickerback.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectAPI {

    private String userId;
    private String socketId;
}
