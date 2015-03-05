package org.asanderson.munifetch.xmlobjects.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aanderson on 3/1/15.
 */

@XmlRootElement(name="direction")
public class PredictionXML {

    @XmlAttribute
    public String epochTime;

    @XmlAttribute
    public String seconds;

    @XmlAttribute
    public String minutes;

    @XmlAttribute
    public String isDeparture;

    @XmlAttribute
    public String dirTag;

    @XmlAttribute
    public String vehicle;

    @XmlAttribute
    public String vehiclesInConsist;

    @XmlAttribute
    public String block;

    @XmlAttribute
    public String tripTag;

    @XmlAttribute
    public String affectedByLayover;

    @XmlAttribute
    public String delayed;

    @XmlAttribute
    public String slowness;
}
