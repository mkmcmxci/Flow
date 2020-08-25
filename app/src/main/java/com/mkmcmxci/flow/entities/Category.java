package com.mkmcmxci.flow.entities;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;

    public Category() {
        super();
    }

    public Category(int id, String name) {
        super();
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


    public List<Category> getCategoryList() {

        List<Category> catList = new ArrayList<>();

        Category c1 = new Category(1, "General");
        Category c2 = new Category(2, "Health");
        Category c3 = new Category(3, "Music");
        Category c4 = new Category(4, "Movies");
        Category c5 = new Category(5, "Programming");
        Category c6 = new Category(6, "Education");
        Category c7 = new Category(7, "Animal");
        Category c8 = new Category(8, "Science");
        Category c9 = new Category(9, "Airbnb");
        Category c10 = new Category(10, "Economics");
        Category c11 = new Category(11, "Sports");
        Category c12 = new Category(12, "Literature");
        Category c13 = new Category(13, "Politics");

        catList.add(c1);
        catList.add(c2);
        catList.add(c3);
        catList.add(c4);
        catList.add(c5);
        catList.add(c6);
        catList.add(c7);
        catList.add(c8);
        catList.add(c9);
        catList.add(c10);
        catList.add(c11);
        catList.add(c12);
        catList.add(c13);

        return catList;
    }

    @Override
    public String toString() {
        return name;
    }
}
