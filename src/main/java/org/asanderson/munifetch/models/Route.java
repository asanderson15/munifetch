package org.asanderson.munifetch.models;

import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */
public class Route {

    private String name;

    private String tag;

    private String color;

    private String oppositeColor;

    private ArrayList<Direction> directions;

    public Route() {
        this.name = "";
        this.tag = "";
        this.color = "333333";
        this.oppositeColor = "FFFFFF";
        this.directions = new ArrayList<Direction>();
    }

    public Route(String name, String tag, String color, String oppositeColor) {
        this.name = name;
        this.tag = tag;
        this.color = color;
        this.oppositeColor = oppositeColor;
        this.directions = new ArrayList<Direction>();
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOppositeColor() {
        return oppositeColor;
    }

    public void setOppositeColor(String oppositeColor) {
        this.oppositeColor = oppositeColor;
    }

    public ArrayList<Direction> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<Direction> directions) {
        this.directions = directions;
    }

    public void addDirection(Direction direction) {
        this.directions.add(direction);
    }

    public Direction getOutbound() {
        for(Direction dir : this.directions) {
            if(dir.getDirection().equals("Outbound")) {
                return dir;
            }
        }
        return this.directions.get(0);
    }

    public Direction getInbound() {
        for(Direction dir : this.directions) {
            if(dir.getDirection().equals("Inbound")) {
                return dir;
            }
        }
        return this.directions.get(0);
    }
}
