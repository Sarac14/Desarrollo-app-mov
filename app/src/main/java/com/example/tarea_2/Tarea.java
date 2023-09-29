package com.example.tarea_2;

public class Tarea {

    //private final String name;
    private final String tarea;
    private final int imageResId;

    public Tarea(String tarea, int image) {
        //this.name = name;
        this.tarea = tarea;
        this.imageResId = image;
    }


    //retrieve user's name


    //retrieve users' hometown
    public String getTarea(){
        return tarea;
    }


    public int getImageResId() {
        return imageResId;
    }

}