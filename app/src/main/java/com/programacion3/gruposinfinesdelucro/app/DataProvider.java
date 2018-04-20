package com.programacion3.gruposinfinesdelucro.app;

public class DataProvider {
    private String img_res;
    private String exercise,duration;
    public DataProvider(String img_res,String exercise,String duration){
        this.setImg_res(img_res);
        this.setExercise(exercise);
        this.setDuration(duration);
    }
    public String getImg_res() {
        return img_res;
    }

    public void setImg_res(String img_res) {
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
