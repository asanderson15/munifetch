package org.asanderson.munifetch.xmlobjects.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by aanderson on 3/1/15.
 */
@XmlRootElement(name="body")
public class MultiPredictionXML {

    @XmlAttribute
    public String copyright;

    @XmlElementWrapper(name="body")
    @XmlElement(name="predictions")
    public ArrayList<PredictionListXML> predictions;

}
