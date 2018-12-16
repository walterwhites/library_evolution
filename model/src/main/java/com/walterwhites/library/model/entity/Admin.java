package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends AbstractUser {
    private Library library;
}
