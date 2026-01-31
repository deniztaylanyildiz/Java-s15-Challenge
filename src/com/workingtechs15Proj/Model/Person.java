package com.workingtechs15Proj.Model;

public abstract class Person {
    private String id;
    private String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void whoYouAre();


    public String getName() { return name; }
    public String getId() { return id; }
}