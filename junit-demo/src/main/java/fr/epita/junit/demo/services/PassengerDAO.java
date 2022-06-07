package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassengerDAO {


    DataSource ds;

    public PassengerDAO(DataSource ds){
        this.ds = ds;
    }


    public void insert(Passenger passenger) throws SQLException {
        Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PASSENGERS(name,age,pclass, survived, gender) values(?,?,?,?,?)");

        preparedStatement.setString(1, passenger.getName());
        preparedStatement.setInt(2, passenger.getAge());
        preparedStatement.setInt(3, passenger.getPclass());
        preparedStatement.setBoolean(4, passenger.isSurvived());
        preparedStatement.setInt(5, passenger.getGender());
        preparedStatement.execute();
    }
}
