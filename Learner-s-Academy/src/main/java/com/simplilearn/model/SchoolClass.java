package com.simplilearn.model;

public class SchoolClass { 

    private int id;
    private String name;
    
    public SchoolClass() {
 
    }

    public SchoolClass(String name) {
        this.name = name;
    }
    
    public SchoolClass(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "SchoolClass [id=" + id + ", name=" + name + "]";
    }
}