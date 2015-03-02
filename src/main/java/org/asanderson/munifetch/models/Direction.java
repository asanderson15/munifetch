package org.asanderson.munifetch.models;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */
public class Direction {

    private String name;

    private String tag;

    private String direction;

    private ArrayList<Stop> stops;

    private DateTime predictionsUpdated;

    public Direction() {
        this.name = "";
        this.tag = "";
        this.direction = "";
        this.stops = new ArrayList<Stop>();
        this.predictionsUpdated = new DateTime(2014,12,31,0,1); // Way longer than 30-60 seconds
    }

    public Direction(String name, String tag, String direction) {
        this.name = name;
        this.tag = tag;
        this.direction = direction;
        this.stops = new ArrayList<Stop>();
        this.predictionsUpdated = new DateTime(2014,12,31,0,1); // Way longer than 30-60 seconds
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

    public void setPredictionsUpdated() {
        this.predictionsUpdated = DateTime.now();
    }

    public Long getTimeSinceLastUpdate() {
        return new Duration(this.predictionsUpdated, DateTime.now()).getStandardSeconds();
    }

}
