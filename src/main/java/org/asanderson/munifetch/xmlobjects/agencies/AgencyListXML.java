package org.asanderson.munifetch.xmlobjects.agencies;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by aanderson on 2/19/15.
 */
@XmlRootElement(name="body")
public class AgencyListXML {

    @XmlElementWrapper(name="body")
    @XmlElement(name="agency")
    public ArrayList<AgencyXML> agency;

    @XmlAttribute
    public String copyright;

    public ArrayList<AgencyXML> getAgencies() {
        return agency;
    }
}
