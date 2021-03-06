package org.asanderson.munifetch.models;

import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */
public class Stop {

    private String name;

    private String tag;

    private String stopId;

    private ArrayList<Prediction> predictions;

    public Stop(String stopId) {
        this.name = "";
        this.tag = "";
        this.stopId = stopId;
    }

    public Stop(String name, String tag, String stopId) {
        this.name = name;
        this.tag = tag;
        this.stopId = stopId;
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

    public synchronized String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public synchronized ArrayList<Prediction> getPredictions() {
        return predictions;
    }

    public synchronized void setPredictions(ArrayList<Prediction> predictions) {
        this.predictions = predictions;
    }

    public synchronized String getPredictionsString() {
        StringBuilder out = new StringBuilder();
        boolean first = true;
        for(Prediction pred : predictions) {
            if(!first) {
                out.append(", ");
            }
            out.append(pred.getMinutes());
            if(pred.getTrainHasLeft().equals("false")) {
                out.append("*");
            }
            first = false;
        }

        return out.toString();
    }
}
