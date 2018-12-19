package com.walterwhites.library.batch.business;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

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

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
