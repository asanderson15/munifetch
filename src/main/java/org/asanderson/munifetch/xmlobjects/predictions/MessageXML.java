package org.asanderson.munifetch.xmlobjects.predictions;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aanderson on 3/1/15.
 */
@XmlRootElement(name="predictions")
public class MessageXML {
    @XmlAttribute
    public String text;

    @XmlAttribute
    public String priority;
}
