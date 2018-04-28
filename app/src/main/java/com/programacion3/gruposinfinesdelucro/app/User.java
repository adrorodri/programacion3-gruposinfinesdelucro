package com.programacion3.gruposinfinesdelucro.app;

public class User {
    private String name;
    private String mail;
    private transient String password;
    private Routines routines;

    public User(String name, String mail, String password, Routines routines) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.routines=routines;
    }
}
