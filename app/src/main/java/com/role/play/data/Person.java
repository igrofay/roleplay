package com.role.play.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_person", primaryKeys = {"id" , "idParent" })
public class Person {
    int id ;
    int idParent;
    String name;
    String classPerson ;
    String item;
    String description;

    public Person(String name, String classPerson , String item ){
        this.name = name;
        this.classPerson = classPerson;
        this.item = item;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPerson() {
        return classPerson;
    }

    public void setClassPerson(String classPerson) {
        this.classPerson = classPerson;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
