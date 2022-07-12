package fr.epita.mob.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean("myFirstBean")
    public String getBean(){
        return "Hello from spring, thomas";
    }


    @Bean("main.ds")
    public DataSource getDataSource(){
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "test", "test");
    }
}
