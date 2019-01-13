package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
public class Client extends AbstractUser {
    private String firstname;
    private String lastname;
    private String language;
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Loan> loans;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Book> books;
}
