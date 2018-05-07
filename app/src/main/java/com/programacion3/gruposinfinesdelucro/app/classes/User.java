package com.programacion3.gruposinfinesdelucro.app.classes;

public class User {
    private String name;
    private String mail;
    private int edad;
    private String metas;
    private transient String password;
    private Rutina mRutina;


    public User(String name, String mail, String password, int edad, String metas) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.mRutina = mRutina;
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

    public String getEmail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Rutina getRutina() {
        return mRutina;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRutina(Rutina rutina) {
        this.mRutina = rutina;
    }
}
