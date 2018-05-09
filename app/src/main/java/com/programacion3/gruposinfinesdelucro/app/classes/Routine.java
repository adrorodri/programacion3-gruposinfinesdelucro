package com.programacion3.gruposinfinesdelucro.app.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaco99 on 05/05/2018.
 */

public class Routine implements Serializable {
    private String name;
    private ArrayList<ArrayList<ScheduledExercise>> daysList = new ArrayList<>();
    private String difficulty;

    public Routine() {
        for (int i = 0; i < 7; i++) {
            daysList.add(new ArrayList<ScheduledExercise>());
        }
    }

    public Routine(String name, String difficulty) {
        this();
        this.name = name;
        this.difficulty = difficulty;
    }

    public void resetDaysList() {
        daysList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daysList.add(new ArrayList<ScheduledExercise>());
        }
    }

    public void addExercise(Enums.Day day, ScheduledExercise exercise) {
        int index = dayToIndex(day);
        daysList.get(index).add(exercise);
    }

    public void addExercise(int day, ScheduledExercise exercise) {
        daysList.get(day).add(exercise);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ArrayList<ScheduledExercise>> getDaysList() {
        return daysList;
    }

    private int dayToIndex(Enums.Day day) {
        int index = 0;
        switch (day) {
            case MONDAY:
                index = 0;
                break;
            case TUESDAY:
                index = 1;
                break;
            case WEDNESDAY:
                index = 2;
                break;
            case THURSDAY:
                index = 3;
                break;
            case FRIDAY:
                index = 4;
                break;
            case SATURDAY:
                index = 5;
                break;
            case SUNDAY:
                index = 6;
                break;
        }
        return index;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

