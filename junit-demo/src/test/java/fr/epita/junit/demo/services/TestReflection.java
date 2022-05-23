package fr.epita.junit.demo.services;

import fr.epita.junit.demo.datamodel.Passenger;

import java.lang.reflect.Field;

public class TestReflection {


    public void testReflection() throws NoSuchFieldException, IllegalAccessException {
        Passenger passenger = new Passenger("test", 32, 3, true, 1);

        Field name = passenger.getClass().getField("name");
        name.setAccessible(true);
        name.set(passenger, "newName");

        Field trueValue = Boolean.class.getField("TRUE");
        trueValue.setAccessible(true);
        trueValue.setBoolean(null, false);
    }
}
