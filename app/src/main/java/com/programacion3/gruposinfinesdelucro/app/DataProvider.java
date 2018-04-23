package com.programacion3.gruposinfinesdelucro.app;

public class DataProvider {
    private int img_res;
    private String exercise, duration;

    public DataProvider(int img_res, String exercise, String duration) {
        this.setImg_res(img_res);
        this.setExercise(exercise);
        this.setDuration(duration);
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
