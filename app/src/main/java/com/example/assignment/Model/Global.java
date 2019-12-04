package com.example.assignment.Model;

import android.app.Application;

public class Global extends Application {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
