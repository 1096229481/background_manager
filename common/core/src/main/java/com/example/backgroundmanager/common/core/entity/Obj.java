package com.example.backgroundmanager.common.core.entity;

import java.util.HashMap;

public class Obj {
    private Person firstPerson;
    private Person lastPerson;

    public Obj() {

    }

    public Obj(Person firstPerson, Person lastPerson) {
        this.firstPerson = firstPerson;
        this.lastPerson = lastPerson;
    }

    public void AddPerson(Person update) {
        System.out.println("2:" + update);
        if (firstPerson == null) {
            this.firstPerson = update;
            this.lastPerson = update;
        } else {
            System.out.println("5:" + lastPerson);
            this.lastPerson.setNextObj(update);
            System.out.println("3:" + lastPerson);
            this.lastPerson = update;
            System.out.println("4:" + lastPerson);
        }
        System.out.println("1:" + firstPerson);
    }


    public static void main(String[] args) {
        Obj obj = new Obj();
        HashMap map = new HashMap<>();
        map.put("name", "huangzd");
        obj.AddPerson(new Person(null, map));
        HashMap map1 = new HashMap<>();
        map1.put("sex", "男");
        obj.AddPerson(new Person(null, map1));
        HashMap map2 = new HashMap<>();
        map2.put("age", 22);
        obj.AddPerson(new Person(null, map2));
        HashMap map3 = new HashMap<>();
        map3.put("grace", 100);
        obj.AddPerson(new Person(null, map3));
    }
}
