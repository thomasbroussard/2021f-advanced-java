package fr.epita.junit.demo.services;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;

public class TestHTTPService {



    @Test
    public void testHttpService() throws IOException {
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



    }
}
