package fr.epita.junit.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.junit.demo.datamodel.Passenger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class TestHTTPService2 {


    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8090);

        HttpServer server = HttpServer.create(socketAddress, 0);

        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                URI requestURI = exchange.getRequestURI();
                String requestMethod = exchange.getRequestMethod();
                System.out.println(requestMethod);
                System.out.println(requestURI.toString());
               // String response = "<html><header></header><body><h1>got something from the server</h1></body></html>";
                Passenger passenger = new Passenger("test", 32, 3, true, 1);

                ObjectMapper mapper = new ObjectMapper();
                String response = mapper.writeValueAsString(passenger);
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
            }
        });

        server.start();
    }

    @Test
    public void testHttpService() throws IOException, URISyntaxException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8089);

        HttpServer server = HttpServer.create(socketAddress, 0);

        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                URI requestURI = exchange.getRequestURI();
                System.out.println(requestURI.toString());
            }
        });

        server.start();

        new URI("http://localhost:8089/test")
                .toURL()
                .openConnection()
                .connect();

        server.stop(1);


    }
}
