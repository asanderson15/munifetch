package org.asanderson.munifetch.xmlobjects.routeconfigs;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */
@XmlRootElement(name="route")
public class DirectionXML {

    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlAttribute
    public String name;

    @XmlAttribute
    public String useForUI;

    @XmlElementWrapper(name="direction")
    @XmlElement(name="stop")
    public ArrayList<DirectionStopXML> stop;

}
