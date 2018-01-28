package com.example.mara.exam.domain;

/**
 * Created by Mara on 1/26/2018.
 */

public class Project {
    private int id;
    private String name;
    private String type;
    private String status;
    private int budget;

    public Project(int id, String name, String type, String status, int budget) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
