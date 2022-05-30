package fr.epita.junit.demo.services;

import fr.epita.conf.ApplicationConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class TestSpring {

    @Inject
    String userName;


    @Test
    public void testInjection(){
        Assertions.assertNotNull(userName);
        System.out.println(userName);
    }


}
