package com.msb.database.tcc.config.entity;

import lombok.Data;



@Data
public class Account {
    private Integer id;
    
    private String userId;
    
    private Integer money;
}
