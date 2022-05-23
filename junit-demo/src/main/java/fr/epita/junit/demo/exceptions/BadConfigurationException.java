package fr.epita.junit.demo.exceptions;

public class BadConfigurationException extends RuntimeException {

    public BadConfigurationException(String message) {
        super(message);
    }
}
