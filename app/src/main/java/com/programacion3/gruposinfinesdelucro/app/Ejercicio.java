package com.programacion3.gruposinfinesdelucro.app;

import java.io.Serializable;

public class Ejercicio implements Serializable{
    private int img_type;
    private int img_desc;
    private String name,duration;
    public Ejercicio(int img_type, String name, String duration){
        this.setImg_type(img_type);
        this.setName(name);
        this.setDuration(duration);
    }
    public int getImg_type() {
        return img_type;
    }

    public void setImg_type(int img_type) {
        this.img_type = img_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImg_desc() {
        return img_desc;
    }

    public void setImg_desc(int img_desc) {
        this.img_desc = img_desc;
    }
}
