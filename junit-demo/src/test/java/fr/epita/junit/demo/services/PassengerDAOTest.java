package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class PassengerDAOTest {


    private Connection cnt;

    @BeforeEach
    public void initialize() throws SQLException {
        this.cnt = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "username", "password");

        PreparedStatement preparedStatement = cnt.prepareStatement("CREATE TABLE IF NOT EXISTS PASSENGERS  (name VARCHAR(255), age INT, pclass INT, survived BOOLEAN, gender INT)");
        preparedStatement.execute();
    }


    @Test
    public void test() throws SQLException {

        //given
        Passenger passenger = new Passenger("test", 30, 3, true, 1);

        //when
        PassengerDAO dao = new PassengerDAO();
        dao.insert(passenger);


        //then
        Connection connection =  cnt;
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
