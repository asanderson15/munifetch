package org.asanderson.munifetch.xmlobjects.routeconfigs;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */


@XmlRootElement(name="route")
public class StopListXML {

    @XmlAnyAttribute
    public String shortTitle;

    @XmlAttribute
    public String color;

    @XmlAttribute
    public String oppositeColor;

    @XmlAttribute
    public String latMin;

    @XmlAttribute
    public String latMax;

    @XmlAttribute
    public String lonMin;

    @XmlAttribute
    public String lonMax;

    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlElementWrapper(name="route")
    @XmlElement(name="stop")
    public ArrayList<StopXML> stop;

    @XmlElementWrapper(name="route")
    @XmlElement(name="direction")
    public ArrayList<DirectionXML> direction;

    @XmlElementWrapper(name="route")
    @XmlElement(name="path")
    public ArrayList<PathXML> path;

    @Override
    public String toString() {
        return "Tag: " + this.tag + ", Title: " + this.title;
    }

}

