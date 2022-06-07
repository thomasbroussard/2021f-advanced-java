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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MovieDataServer {


    public static List<Movie> movies = new ArrayList<>();

    static {
        movies.add(new Movie(1,"Star Wars"));
        movies.add(new Movie(0,"the Matrix"));
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
                        String request = requestURI.toString();
                        Pattern pattern = Pattern.compile("/movies/(.*)");
                        Matcher matcher = pattern.matcher(request);
                        // case where we want a single movie
                        if (matcher.matches()){
                            String id = matcher.group(1);
                            List<Movie> collect = movies.stream().filter(m -> m.getId() == Integer.parseInt(id)).collect(Collectors.toList());
                            response = mapper.writeValueAsString(collect);
                        } else { //or we want all the list of movies
                            //TODO check if the pattern is correct
                            response = mapper.writeValueAsString(movies);
                        }
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
