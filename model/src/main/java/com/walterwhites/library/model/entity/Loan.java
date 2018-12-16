package com.walterwhites.library.model.entity;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Loan")
@Getter
@Setter
@NoArgsConstructor
public class Loan {
    private int id;
    private Date start_date;
    private Date end_date;
    private Client client;
    private Bool renewed;
    private List<Book> books;
    private String state;
    private Date updated_date;
}
