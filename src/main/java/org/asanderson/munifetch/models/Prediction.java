package org.asanderson.munifetch.models;

/**
 * Created by aanderson on 2/22/15.
 */
public class Prediction {

    public String minutes;

    public String trainHasLeft;

    public Prediction() {
        this.minutes = "0";
        this.trainHasLeft = "true";
    }

    public Prediction(String minutes, String trainHasLeft) {
        this.minutes = minutes;
        this.trainHasLeft = trainHasLeft;
    }

    public synchronized String getMinutes() {
        if(this.minutes.equals("0")) {
            return "Arriving";
        }
        return minutes;
    }

    public synchronized void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public synchronized String getTrainHasLeft() {
        return trainHasLeft;
    }

    public synchronized void setTrainHasLeft(String trainHasLeft) {
        this.trainHasLeft = trainHasLeft;
    }
}
