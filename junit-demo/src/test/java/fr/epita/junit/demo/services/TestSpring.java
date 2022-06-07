package fr.epita.junit.demo.services;

import fr.epita.conf.ApplicationConfiguration;
import fr.epita.junit.demo.datamodel.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TestSpring {


    static {
        System.setProperty("conf.file", "src/test/resources/conf.properties");
    }

    @Inject
    @Named("userName")
    String userName;


    @Inject
    @Named("db.mainDatasource")
    DataSource dataSource;

    @Inject
    @Named("services.dao.passengersDAO")
    PassengerDAO dao;


    @BeforeEach
    public void initialize() throws SQLException {
        Connection cnt = this.dataSource.getConnection();
        PreparedStatement preparedStatement = cnt.prepareStatement("CREATE TABLE IF NOT EXISTS PASSENGERS  (name VARCHAR(255), age INT, pclass INT, survived BOOLEAN, gender INT)");
        preparedStatement.execute();
    }


    @Test
    public void testInjection(){
        Assertions.assertNotNull(userName);
        System.out.println(userName);
    }

    @Test
    public void testConnectionFromInject() throws SQLException {
        Assertions.assertNotNull(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getSchema());
        connection.close();
    }

    @Test
    public void testPassengerDao() throws SQLException {
        this.dao.insert(new Passenger("blah blah", 30, 1, true, 0));
    }


    @Test
    public void testConnectionFromInjectWithClose() throws SQLException {
        Assertions.assertNotNull(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getSchema());
        connection.close();
    }

}
