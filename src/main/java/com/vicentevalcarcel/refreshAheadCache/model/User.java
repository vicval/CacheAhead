package com.vicentevalcarcel.refreshAheadCache.model;

import lombok.Data;

@Data
public class User {
    Integer id;

    public User(Integer id) {
        this.id = id;
    }
}
