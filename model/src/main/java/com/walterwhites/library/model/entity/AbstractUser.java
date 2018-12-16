package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AbstractUser {
    protected int id;
    protected String password;
    protected String email;
    protected Date created_at;
    protected Date updated_at;
}
