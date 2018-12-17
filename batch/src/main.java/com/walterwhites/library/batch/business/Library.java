package com.walterwhites.library.batch.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Library {
    private String name;
    private String phoneNumber;
    private String address;

    @Override
    public String toString() {
        return this.name;
    }
}
