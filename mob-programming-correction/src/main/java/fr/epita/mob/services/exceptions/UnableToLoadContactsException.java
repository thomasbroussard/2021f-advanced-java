package fr.epita.mob.services.exceptions;

import java.io.IOException;

public class UnableToLoadContactsException extends Exception {

    public UnableToLoadContactsException(Exception technicalException) {
        initCause(technicalException);
    }
}
