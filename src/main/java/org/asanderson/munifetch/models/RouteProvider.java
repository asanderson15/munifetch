package org.asanderson.munifetch.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.asanderson.munifetch.xmlobjects.routeconfigs.DirectionStopXML;
import org.asanderson.munifetch.xmlobjects.routeconfigs.DirectionXML;
import org.asanderson.munifetch.xmlobjects.routeconfigs.RouteConfigXML;
import org.asanderson.munifetch.xmlobjects.routeconfigs.StopXML;
import org.asanderson.munifetch.xmlobjects.routes.RouteListXML;
import org.asanderson.munifetch.xmlobjects.routes.RouteXML;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aanderson on 2/22/15.
 */
public class RouteProvider {

    private ArrayList<String> routeIds;

    private HashMap<String,Route> loadedRoutes;

    public RouteProvider() {
        routeIds = new ArrayList<String>();
        loadedRoutes = new HashMap<String, Route>();
    }

    public void retrieveListOfRoutes() {

        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=sf-muni");
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        String responseText = response.readEntity(String.class);
        System.out.println(responseText);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        try {
            RouteListXML list = mapper.readValue(responseText, RouteListXML.class);
            for(RouteXML route : list.route) {
                routeIds.add(route.tag);
            }
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private RouteConfigXML retrieveRouteConfigXML(String routeId) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=routeConfig&a=sf-muni&r=" + routeId);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        String responseText = response.readEntity(String.class);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        RouteConfigXML routes;
        try {
            routes = mapper.readValue(responseText, RouteConfigXML.class);
        }
        catch(Exception e) {
            routes = new RouteConfigXML();
            System.out.println(e.getLocalizedMessage());
        }

        return routes;
    }

    public Route retrieveRoute(String routeId) {

        if(this.loadedRoutes.containsKey(routeId)) {
            return loadedRoutes.get(routeId);
        }

        // Get data from the internet
        RouteConfigXML xml = this.retrieveRouteConfigXML(routeId);

        // Map stop tag to stop details
        HashMap<String, StopXML> stopMap = new HashMap<String, StopXML>();
        for(StopXML sxml : xml.route.stop) {
            stopMap.put(sxml.tag, sxml);
        }

        // Create route object
        Route route = new Route(xml.route.title,
                xml.route.tag,
                xml.route.color,
                xml.route.oppositeColor);

        for(DirectionXML dir : xml.route.direction) {
            Direction direction = new Direction(dir.title, dir.tag, dir.name);

            // Map stops
            for(DirectionStopXML dsxml: dir.stop) {
                Stop stop = new Stop(dsxml.tag);
                stop.setName(stopMap.get(dsxml.tag).title);
                stop.setStopId(stopMap.get(dsxml.tag).stopId);
                direction.addStop(stop);
            }

            // add direction to the route
            route.addDirection(direction);
        }

        // Cache route for fast retrieval later
        this.loadedRoutes.put(route.getTag(), route);

        return route;

    }


}
