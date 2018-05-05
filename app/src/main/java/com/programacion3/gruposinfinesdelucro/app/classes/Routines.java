package com.programacion3.gruposinfinesdelucro.app.classes;

import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;

import java.util.List;

public class Routines {
    private String creador;
    private String nombre;
    private String dificultad;
    private List<Ejercicio> ejercicios;

    public Routines(String nombre,String creador,String dificultad,List<Ejercicio> ejercicios) {
        this.nombre=nombre;
        this.creador=creador;
        this.dificultad=dificultad;
        this.ejercicios=ejercicios;
    }
    public Routines(String nombre,String dificultad,List<Ejercicio> ejercicios) {
        this.nombre=nombre;
        this.dificultad=dificultad;
        this.ejercicios=ejercicios;
    }


    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
