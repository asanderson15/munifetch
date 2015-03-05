package org.asanderson.munifetch.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized String getTag() {
        return tag;
    }

    public synchronized void setTag(String tag) {
        this.tag = tag;
    }

    public synchronized String getDirection() {
        return direction;
    }

    public synchronized void setDirection(String direction) {
        this.direction = direction;
    }

    public synchronized ArrayList<Stop> getStops() {
        return stops;
    }

    public synchronized void setStops(ArrayList<Stop> stops) {
        this.stops = stops;
    }

    public synchronized void addStop(Stop stop) {
        this.stops.add(stop);
    }

    public synchronized void setPredictionsUpdated() {
        this.predictionsUpdated = DateTime.now();
    }

    public synchronized Long getTimeSinceLastUpdate() {
        return new Duration(this.predictionsUpdated, DateTime.now()).getStandardSeconds();
    }

}
