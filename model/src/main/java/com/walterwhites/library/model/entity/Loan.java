package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Loan")
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date start_date;
    private Date end_date;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Client client;
    private Boolean renewed;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Book> books;
    private String state;
    private Date updated_date;
}