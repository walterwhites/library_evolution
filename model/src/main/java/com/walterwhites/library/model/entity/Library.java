package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Library")
@Getter
@Setter
@NoArgsConstructor
public class Library {
    private String name;
    private String phoneNumber;
    private String address;
}
