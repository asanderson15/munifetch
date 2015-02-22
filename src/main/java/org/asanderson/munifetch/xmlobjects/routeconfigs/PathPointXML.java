package org.asanderson.munifetch.xmlobjects.routeconfigs;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aanderson on 2/22/15.
 */

@XmlRootElement(name="point")
public class PathPointXML {

    @XmlAttribute
    public String lat;

    @XmlAttribute
    public String lon;
}
