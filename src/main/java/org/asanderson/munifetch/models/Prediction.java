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

    public String getMinutes() {
        if(this.minutes.equals("0")) {
            return "Arriving";
        }
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getTrainHasLeft() {
        return trainHasLeft;
    }

    public void setTrainHasLeft(String trainHasLeft) {
        this.trainHasLeft = trainHasLeft;
    }
}
