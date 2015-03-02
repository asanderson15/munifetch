package org.asanderson.munifetch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.asanderson.munifetch.models.Route;
import org.asanderson.munifetch.models.RouteProvider;
import org.asanderson.munifetch.models.Stop;
import org.asanderson.munifetch.xmlobjects.agencies.AgencyListXML;
import org.asanderson.munifetch.xmlobjects.predictions.MultiPredictionXML;
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
        // testRouteConfig();
        // testPredictions();

        RouteProvider routeProvider = new RouteProvider();
        Route nJudah = routeProvider.retrieveRoute("N");
        routeProvider.retrievePredictions(nJudah.getOutbound());

        System.out.println(nJudah.getName());
        System.out.println(nJudah.getColor());
        System.out.println(nJudah.getOppositeColor());
        System.out.println(nJudah.getOutbound().getStops().get(0).getName());
        System.out.println(nJudah.getInbound().getStops().get(0).getName());

        for(Stop stop : nJudah.getOutbound().getStops()) {
            System.out.println(stop.getName() + " - " + stop.getPredictionsString());
        }


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

    public static void testPredictions() throws Exception {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webTarget = client.target("http://webservices.nextbus.com/service/publicXMLFeed?command=predictionsForMultiStops&a=sf-muni&stops=N%7C6997&stops=N%7C3909");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

        Response response = invocationBuilder.get();

        System.out.println(response.getStatus());
        String responseText = response.readEntity(String.class);
        System.out.println(responseText);

        // Set up Jackson XML
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        ObjectMapper mapper = new XmlMapper(module);

        MultiPredictionXML predictions = mapper.readValue(responseText, MultiPredictionXML.class);
        System.out.println(predictions.predictions.get(0).direction.get(0).prediction.get(0).minutes);
    }
}