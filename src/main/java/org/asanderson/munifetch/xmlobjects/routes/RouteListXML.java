package org.asanderson.munifetch.xmlobjects.routes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * Created by aanderson on 2/20/15.
 */
public class RouteListXML {

    @XmlElementWrapper(name="body")
    @XmlElement(name="route")
    public ArrayList<RouteXML> route;

    @XmlAttribute
    public String copyright;
}
