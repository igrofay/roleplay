package com.role.play.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "t_place")
public class Place {
    @PrimaryKey
    private int id ;
    private String setting;
    private String room ;
    @Ignore
    private List<Person> persons;
    private String descriptionRoom;

    public Place(@NotNull String setting, @NotNull String room, @NotNull List<Person> listPerson) {
        this.setting =setting;
        this.room = room;
        this.persons = listPerson;
    }
    public Place(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getDescriptionRoom() {
        return descriptionRoom;
    }

    public void setDescriptionRoom(String descriptionRoom) {
        this.descriptionRoom = descriptionRoom;
    }
}
