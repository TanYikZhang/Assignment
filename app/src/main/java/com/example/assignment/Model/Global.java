package com.example.assignment.Model;

import android.app.Application;

public class Global extends Application {

    private int id;
    private int viewaccess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViewaccess() {
        return viewaccess;
    }

    public void setViewaccess(int viewaccess) {
        this.viewaccess = viewaccess;
    }
}
