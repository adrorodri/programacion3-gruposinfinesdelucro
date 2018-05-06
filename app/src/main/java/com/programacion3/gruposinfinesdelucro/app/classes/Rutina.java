package com.programacion3.gruposinfinesdelucro.app.classes;

import com.programacion3.gruposinfinesdelucro.app.classes.Ejercicio;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String creador;
    private String nombre;
    private String dificultad;
    private List<Ejercicio> ejercicios;
    private List<List<List<Ejercicio>>> rutina = new ArrayList<>();

    public Rutina(String nombre, String creador, String dificultad, List<Ejercicio> ejercicios) {
        this.nombre=nombre;
        this.creador=creador;
        this.dificultad=dificultad;
        this.ejercicios=ejercicios;
    }

    public Rutina(String nombre, String dificultad, List<Ejercicio> ejercicios) {
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.ejercicios = ejercicios;
    }


    public String getCreador() {
        return creador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDificultad() {
        return dificultad;
    }
}
