package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassengerDAO {



    public void insert(Passenger passenger) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "username", "password");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PASSENGERS(name,age,pclass, survived, gender) values(?,?,?,?,?)");
        // parameters mapping TODO
        // preparedStatement.setString();
        preparedStatement.execute();
    }
}
