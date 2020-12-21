package com.example.backgroundmanager.common.core.entity;

import java.util.Map;

public class Person {
    private  Object nextObj;
    private Map<String,Object> proyod;

    public Person() {
    }

    public Person(Map<String, Object> proyod) {
        this.proyod = proyod;
    }

    public Person(Object nextObj, Map<String, Object> proyod) {
        this.nextObj = nextObj;
        this.proyod = proyod;
    }

    public Object getNextObj() {
        return nextObj;
    }

    public void setNextObj(Object nextObj) {
        this.nextObj = nextObj;
    }

    public void setProyod(Map<String, Object> proyod) {
        this.proyod = proyod;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nextObj=" + nextObj +
                ", proyod=" + proyod +
                '}';
    }
}
