package com.lei.sqladapterdemo;

/**
 * Created by yanle on 2018/2/8.
 */

public class Person {
    private int _id;
    private String name;
    private int age;

    public Person(int _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
