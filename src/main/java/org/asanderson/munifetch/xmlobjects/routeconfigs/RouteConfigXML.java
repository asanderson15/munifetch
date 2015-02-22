package org.asanderson.munifetch.xmlobjects.routeconfigs;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aanderson on 2/22/15.
 */

@XmlRootElement(name="body")
public class RouteConfigXML {

    @XmlAttribute
    public String copyright;

    @XmlElement(name="route")
    public StopListXML route;

}
