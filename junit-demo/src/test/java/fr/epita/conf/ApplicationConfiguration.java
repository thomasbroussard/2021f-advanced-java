package fr.epita.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
