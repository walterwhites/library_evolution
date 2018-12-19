package com.walterwhites.library.batch.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Client extends AbstractUser {
    private String firstname;
    private String lastname;
    private String language;

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}
