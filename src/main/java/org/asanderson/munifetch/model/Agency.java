package org.asanderson.munifetch.model;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by aanderson on 2/19/15.
 */
@XmlRootElement(name="agency")
public class Agency {

    @XmlAnyAttribute
    public String shortTitle;

    @XmlAttribute
    public String tag;

    @XmlAttribute
    public String title;

    @XmlAttribute
    public String regionTitle;

    @Override
    public String toString() {
        return "Tag: " + this.tag + ", Title: " + this.title;
    }

}
