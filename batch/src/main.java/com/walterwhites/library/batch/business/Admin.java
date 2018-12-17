package com.walterwhites.library.batch.business;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Admin extends AbstractUser {
    private Library library;

    @Override
    public String toString() {
        return "Admin id:" + this.id;
    }
}
