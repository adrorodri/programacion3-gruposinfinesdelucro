package com.programacion3.gruposinfinesdelucro.app;

public class User {
    private String name;
    private String mail;
    private int edad;
    private String metas;
    private transient String password;
    private Routines routines;
    public User(String name, String mail, String password, Routines routines, int edad, String metas) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.routines = routines;
        this.edad = edad;
        this.metas = metas;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Routines getRoutines() {
        return routines;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoutines(Routines routines) {
        this.routines = routines;
    }

}
