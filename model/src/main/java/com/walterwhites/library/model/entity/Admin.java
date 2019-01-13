package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends AbstractUser {
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Library library;
}