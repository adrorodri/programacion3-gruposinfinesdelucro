package com.programacion3.gruposinfinesdelucro.app.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaco99 on 05/05/2018.
 */

public class Routine {
    private String name;
    private List<List<ScheduledExercise>> daysList = new ArrayList<>();

    public Routine(){
        for(int i = 0; i < 7; i++){
            daysList.add(new ArrayList<ScheduledExercise>());
        }
    }

    public Routine(String name){
        this();
        this.name = name;
    }

    public void addExercise(Enums.Day day, ScheduledExercise exercise){
        int index = dayToIndex(day);
        daysList.get(index).add(exercise);
    }

    public String getName() {
        return name;
    }

    public List<List<ScheduledExercise>> getDaysList() {
        return daysList;
    }

    private int dayToIndex(Enums.Day day){
        int index = 0;
        switch (day){
            case LUNES:
                index = 0;
                break;
            case MARTES:
                index = 1;
                break;
            case MIERCOLES:
                index = 2;
                break;
            case JUEVES:
                index = 3;
                break;
            case VIERNES:
                index = 4;
                break;
            case SABADO:
                index = 5;
                break;
            case DOMINGO:
                index = 6;
                break;
        }
        return index;
    }
}

