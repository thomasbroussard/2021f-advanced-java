package fr.epita.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public String userName(){
       return "testUsername";
    }

    @Bean
    public String userName2(){
        return "testUsername";
    }


    @Bean("conf.mainConf")
    public fr.epita.junit.demo.services.Configuration getConf(){
        return fr.epita.junit.demo.services.Configuration.getInstance();
    }

    @Bean("db.mainSqlConnection")
    public Connection getConnection(@Qualifier("conf.mainConf") fr.epita.junit.demo.services.Configuration conf) throws SQLException {
       return DriverManager.getConnection(conf.getDBUrl(), conf.getDBUser(), conf.getDBPassword());
    }


    @Bean("db.mainDatasource")
    public DataSource dataSource(@Qualifier("conf.mainConf") fr.epita.junit.demo.services.Configuration conf){
        return new DriverManagerDataSource(conf.getDBUrl(), conf.getDBUser(), conf.getDBPassword());

    }
}
