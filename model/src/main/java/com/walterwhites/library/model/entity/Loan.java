package com.walterwhites.library.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Book book;
    private Date updated_date;
    private String state;
}