package fr.epita.mob.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestSPR1 {

    @Inject
    @Named("myFirstBean")
    private String str;

    @Test
    public void test(){
        //check it against the same sentence to verify if spring is correctly loaded
        Assertions.assertEquals(str, "Hello from spring, thomas");
    }



}
