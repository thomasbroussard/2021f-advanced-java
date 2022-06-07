package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.File;
import java.sql.*;


public class PassengerDAOTest {

    static{
        System.setProperty("conf.file","src/test/resources/conf.properties");
    }

    private Connection cnt;

    @BeforeEach
    public void initialize() throws SQLException {
        this.cnt = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "username", "password");

        PreparedStatement preparedStatement = cnt.prepareStatement("CREATE TABLE IF NOT EXISTS PASSENGERS  (name VARCHAR(255), age INT, pclass INT, survived BOOLEAN, gender INT)");
        preparedStatement.execute();
    }


    @Test
    @DisplayName("when a passenger is inserted with valid values in the database, then it should be found")
    public void test() throws SQLException {

        //given
        Passenger passenger = new Passenger("test", 30, 3, true, 1);

        Configuration conf = Configuration.getInstance();
        DriverManagerDataSource ds = new DriverManagerDataSource(conf.getDBUrl(), conf.getDBUser(), conf.getDBPassword());

        //when
        PassengerDAO dao = new PassengerDAO(ds);
        dao.insert(passenger);


        //then
        Connection connection =  cnt;
        ResultSet resultSet = connection.prepareStatement("select * from passengers where name = 'test'").executeQuery();
        String retrievedName = null;
        while (resultSet.next()) {
            retrievedName = resultSet.getString("name");
        }
        Assertions.assertNotNull(retrievedName);
    }


}
