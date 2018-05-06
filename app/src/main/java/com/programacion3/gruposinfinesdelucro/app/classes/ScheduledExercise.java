package com.programacion3.gruposinfinesdelucro.app.classes;

/**
 * Created by Joaco99 on 05/05/2018.
 */

public class ScheduledExercise {
    private Exercise exercise;
    private int sec_duration = 0, repetitions;

    public ScheduledExercise(){

    }

    public ScheduledExercise(Exercise exercise, int repetitions) {
        this.exercise = exercise;
        this.repetitions = repetitions;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getSec_duration() {
        return sec_duration;
    }

    public int getRepetitions() {
        return repetitions;
    }
}

