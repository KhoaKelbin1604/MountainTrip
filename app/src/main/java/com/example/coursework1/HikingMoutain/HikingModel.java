package com.example.coursework1.HikingMoutain;

import java.io.Serializable;

public class HikingModel implements Serializable{
    private int trip_id;
    private String name, elevation, distance,  date, location, description;
    private int parking_available, td, level;

    public HikingModel() {

    }
    public int getId(){
        return trip_id;
    }
    public void setId(int trip_id){
        this.trip_id = trip_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getParking_availableSelected() {
        return parking_available;
    }

    public void setParking_available(int parkingAvailable) {
        this.parking_available = parkingAvailable;
    }

    public int getTdSelected() {
        return td;
    }

    public void setTd(int td) {
        this.td = td;
    }

    public int getLevelSelected() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
