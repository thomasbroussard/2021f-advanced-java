package fr.epita.jpa.tests;

import org.h2.tools.Server;

import java.sql.SQLException;

public class TestH2Server {

    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer("-tcpPort", "9123", "-tcpAllowOthers").start();
        //TODO complete server config

    }

}
