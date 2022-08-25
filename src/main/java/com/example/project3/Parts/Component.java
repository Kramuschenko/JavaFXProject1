package com.example.project3.Parts;

import java.util.ArrayList;

public class Component {

    static String[] tastes = new String[]{"słodki", "słony", "gorzki","kwaśny", "neutralny"};

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", taste='" + taste + '\'' +
                '}';
    }

    public static String[] getTastes() {
        return tastes;
    }

    private String name;
    private int weight;
    private String taste;

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getTaste() {

        int i1 = 0;

        for (int i = 0; i < tastes.length; i++) {
            if (tastes[i] == taste)
                i1= i;
        }

        return  i1;
    }

    public Component(String name, int taste, int weight) {
        this.name = name;
        this.taste = tastes[taste];
        this.weight = weight;
    }
}
