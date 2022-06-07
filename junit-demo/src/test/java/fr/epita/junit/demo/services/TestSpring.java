package fr.epita.junit.demo.services;

import fr.epita.conf.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
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
    public void testConnectionFromInjectWithClose() throws SQLException {
        Assertions.assertNotNull(dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println(connection.getSchema());
        connection.close();
    }

}
