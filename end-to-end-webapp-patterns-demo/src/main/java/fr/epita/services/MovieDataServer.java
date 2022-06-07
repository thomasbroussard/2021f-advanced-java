package fr.epita.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.datamodel.Movie;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDataServer {


    public static List<Movie> movies = new ArrayList<>();

    static {
        movies.add(new Movie(0,"the Matrix"));
        movies.add(new Movie(1,"Star Wars"));
        movies.add(new Movie(2,"Alien"));
    }


    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8089);

        HttpServer server = HttpServer.create(socketAddress, 0);

        server.createContext("/movies", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                URI requestURI = exchange.getRequestURI();
                String requestMethod = exchange.getRequestMethod();
                String response = "";
                switch (requestMethod){

                    case "GET":
                        ObjectMapper mapper = new ObjectMapper();
                        response = mapper.writeValueAsString(movies);
                        break;
                    case "POST":
                        break;

                    default:
                        System.out.println("not implemented");
                }
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            }
        });
        server.start();




    }
}
