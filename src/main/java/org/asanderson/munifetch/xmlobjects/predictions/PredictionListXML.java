package org.asanderson.munifetch.xmlobjects.predictions;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Created by aanderson on 3/1/15.
 */

@XmlRootElement(name="predictions")
public class PredictionListXML {

    @XmlAttribute
    public String agencyTitle;

    @XmlAttribute
    public String routeTitle;

    @XmlAttribute
    public String routeTag;

    @XmlAttribute
    public String stopTitle;

    @XmlAttribute
    public String stopTag;

    @XmlElementWrapper(name="predictions")
    @XmlElement(name="message")
    public ArrayList<MessageXML> message;

    @XmlElementWrapper(name="predictions")
    @XmlElement(name="direction")
    public ArrayList<DirectionXML> direction;
}
