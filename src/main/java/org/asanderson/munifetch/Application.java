package org.asanderson.munifetch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.asanderson.munifetch.xmlobjects.agencies.AgencyListXML;
import org.asanderson.munifetch.xmlobjects.routeconfigs.RouteConfigXML;
import org.asanderson.munifetch.xmlobjects.routes.RouteListXML;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Application {
    public static void main(String[] args) throws Exception {

        // testAgencyList();
        // testRouteList();
        testRouteConfig();

    }

    public static void testAgencyList() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=agencyList");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        String responseText = response.readEntity(String.class);
        System.out.println(responseText);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        AgencyListXML list = mapper.readValue(responseText, AgencyListXML.class);
        System.out.println(list.getAgencies().get(0).toString());
    }

    public static void testRouteList() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=sf-muni");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        String responseText = response.readEntity(String.class);
        System.out.println(responseText);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        RouteListXML list = mapper.readValue(responseText, RouteListXML.class);
        System.out.println(list.route.get(0).toString());
    }

    public static void testRouteConfig() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=sf-muni&r=N");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        String responseText = response.readEntity(String.class);
        System.out.println(responseText);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        RouteConfigXML routes = mapper.readValue(responseText, RouteConfigXML.class);
        System.out.println(routes.route.stop.get(0).title);
    }
}