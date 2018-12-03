package com.walterwhites.library.model.entity;

public class Admin extends AbstractUser {

    private Library library;

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
