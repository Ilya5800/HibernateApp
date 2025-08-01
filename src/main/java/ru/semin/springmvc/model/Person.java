package ru.semin.springmvc.model;


import jakarta.persistence.*;
import ru.semin.springmvc.model.Item;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name= "name")
    private String name;
    @Column(name = "age")
    private int age;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Item> items;

    public Person() {

    }

    public Person( String name, int age) {

        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + ", " + this.id;
    }
}
