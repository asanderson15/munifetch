package org.asanderson.munifetch.models;

import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */
public class Direction {

    private String name;

    private String tag;

    private String direction;

    private ArrayList<Stop> stops;

    public Direction() {
        this.name = "";
        this.tag = "";
        this.direction = "";
        this.stops = new ArrayList<Stop>();
    }

    public Direction(String name, String tag, String direction) {
        this.name = name;
        this.tag = tag;
        this.direction = direction;
        this.stops = new ArrayList<Stop>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public void addStop(Stop stop) {
        this.stops.add(stop);
    }

}
