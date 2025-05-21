package com.Sawa.entity;

import lombok.Data;

/**
 * @author Sawa
 * @version 1.0
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
}
