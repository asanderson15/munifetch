package org.asanderson.munifetch.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.asanderson.munifetch.xmlobjects.predictions.MultiPredictionXML;
import org.asanderson.munifetch.xmlobjects.predictions.PredictionListXML;
import org.asanderson.munifetch.xmlobjects.predictions.PredictionXML;
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

    /***************************************
        Retrieves list of routes from server
     ***************************************/

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





    /*************************************************************************
     Checks cache; if new route, pulls details from the server and parses them
     *************************************************************************/

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
            Direction direction = new Direction(dir.title, route.getTag(), dir.name);

            // Map stops
            for(DirectionStopXML dsxml: dir.stop) {
                Stop stop = new Stop(dsxml.tag);
                stop.setName(stopMap.get(dsxml.tag).title);
                stop.setTag(route.getTag());
                direction.addStop(stop);
            }

            // add direction to the route
            route.addDirection(direction);
        }

        // Cache route for fast retrieval later
        this.loadedRoutes.put(route.getTag(), route);

        return route;

    }

    /*************************************************************************
     Given a route direction, retrieves predictions
     *************************************************************************/

    public Direction retrievePredictions(Direction direction) {

        // Make sure prediction not already recent enough
        if(direction.getTimeSinceLastUpdate() < 30) {
            return direction;
        }

        // Get predictions from server
        MultiPredictionXML predictions = retrievePredictionXML(direction);

        // Map stops to index in array of stops
        HashMap<String,Integer> stopMap = new HashMap<String, Integer>();
        int i = 0;
        for(Stop stop : direction.getStops()) {
            stopMap.put(stop.getStopId(), i);
            i++;
        }

        // Parse through predictions and store in stop objects
        for(PredictionListXML pl : predictions.predictions) {
            String stopId = pl.stopTag;
            if(stopMap.containsKey(stopId)) {
                Stop stop = direction.getStops().get(stopMap.get(stopId));
                ArrayList<Prediction> predictionArrayList = new ArrayList<Prediction>();
                for(PredictionXML pred : pl.direction.get(0).prediction) {
                    Prediction predictionObject = new Prediction();
                    predictionObject.setMinutes(pred.minutes);
                    if(pred.affectedByLayover != null && pred.affectedByLayover.equals("true")) {
                        predictionObject.setTrainHasLeft("false"); // train hasn't left
                    } else {
                        predictionObject.setTrainHasLeft("true"); // train has left
                    }
                    predictionArrayList.add(predictionObject);
                }
                stop.setPredictions(predictionArrayList);
            }
        }

        return direction;

    }


    /***************************************************
     Retrieves details of a particular route from server
     ***************************************************/

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

    /***************************************************
     Retrieves details of a particular route from server
     ***************************************************/

    private MultiPredictionXML retrievePredictionXML(Direction direction) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        String url = "http://webservices.nextbus.com/service/publicXMLFeed?command=predictionsForMultiStops&a=sf-muni" +
                createStopListForURL(direction);
        System.out.println(url);
        WebTarget webTarget = client.target(url);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        String responseText = response.readEntity(String.class);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        MultiPredictionXML predictions;
        try {
            predictions = mapper.readValue(responseText, MultiPredictionXML.class);
        }
        catch(Exception e) {
            predictions = new MultiPredictionXML();
            System.out.println(e.getLocalizedMessage());
        }

        return predictions;
    }


    /***************************************************
     Provides URL for route prediction gathering
     ***************************************************/

    private String createStopListForURL(Direction direction) {

        StringBuilder stopList = new StringBuilder();

        for(Stop stop : direction.getStops()) {
            stopList.append("&stops=")
                    .append(direction.getTag())
                    .append("%7C")
                    .append(stop.getStopId());
        }

        return stopList.toString();

    }





}
