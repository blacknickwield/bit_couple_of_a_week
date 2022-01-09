package com.gjf.bit_couple_of_a_week.exception;

public class UserDaoException extends RuntimeException {
    public UserDaoException() {
    }

    public UserDaoException(String message) {
        super(message);
    }
}
