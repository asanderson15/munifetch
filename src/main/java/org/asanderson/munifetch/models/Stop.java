package org.asanderson.munifetch.models;

/**
 * Created by aanderson on 2/22/15.
 */
public class Stop {

    private String name;

    private String tag;

    private String stopId;

    public Stop(String tag) {
        this.name = "";
        this.tag = tag;
        this.stopId = "";
    }

    public Stop(String name, String tag, String stopId) {
        this.name = name;
        this.tag = tag;
        this.stopId = stopId;
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

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }
}
