package org.asanderson.munifetch.xmlobjects.routeconfigs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by aanderson on 2/22/15.
 */

@XmlRootElement(name="path")
public class PathXML {

    @XmlElementWrapper(name="path")
    @XmlElement(name="point")
    public ArrayList<PathPointXML> point;

}
