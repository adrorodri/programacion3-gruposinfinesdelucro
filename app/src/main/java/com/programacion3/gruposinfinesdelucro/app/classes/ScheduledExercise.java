package com.programacion3.gruposinfinesdelucro.app.classes;

import java.io.Serializable;

/**
 * Created by Joaco99 on 05/05/2018.
 */

public class ScheduledExercise implements Serializable{
    private Exercise exercise;
    private int sec_duration = -1 , repetitions = -1, series = -1;

    public ScheduledExercise() {

    }

    public ScheduledExercise(Exercise exercise, int repetitions, int series) {
        this.exercise = exercise;
        this.repetitions = repetitions;
        this.series  = series;
    }

    public ScheduledExercise(Exercise exercise, int sec_duration) {
        this.exercise = exercise;
        this.sec_duration = sec_duration;
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

    public int getSeries() {
        return series;
    }
}

