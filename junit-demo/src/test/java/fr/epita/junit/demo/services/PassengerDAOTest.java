package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDAOTest {


    @Test
    public void test() throws SQLException {
        //given
        Passenger passenger = new Passenger("test", 30, 3, true, 1);

        //when
        PassengerDAO dao = new PassengerDAO();
        dao.insert(passenger);


        //then
        Connection connection = DriverManager.getConnection("");
        ResultSet resultSet = connection.prepareStatement("select * from passengers where name = 'test'").executeQuery();
        String retrievedName = null;
        while (resultSet.next()) {
            retrievedName = resultSet.getString("name");
        }
        if (retrievedName == null) {
            throw new RuntimeException("the name test was not found, expected to be not null");
        }
    }


}
