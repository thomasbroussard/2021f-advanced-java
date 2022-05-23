package fr.epita.junit.demo.services;

import org.junit.jupiter.api.Test;

public class TestConfiguration {


    static{
        System.setProperty("conf.file","src/test/resources/conf.properties");
    }

    @Test
    public void testFetchValue(){
        Configuration configuration = Configuration.getInstance();

    }
}
