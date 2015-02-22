package org.asanderson.munifetch.xmlobjects.routes;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aanderson on 2/20/15.
 */

@XmlRootElement(name="route")
public class RouteXML {

    @XmlAnyAttribute
    public String shortTitle;

    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @Override
    public String toString() {
        return "Tag: " + this.tag + ", Title: " + this.title;
    }

}
