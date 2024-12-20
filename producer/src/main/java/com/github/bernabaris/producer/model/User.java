package com.github.bernabaris.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private String address;
    private String email;
    private String phone;
    private String company;
    private String country;
}
