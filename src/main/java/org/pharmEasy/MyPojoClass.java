package org.pharmEasy;

public class MyPojoClass {

    String name;
    int id;

    public MyPojoClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyPojoClass{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
