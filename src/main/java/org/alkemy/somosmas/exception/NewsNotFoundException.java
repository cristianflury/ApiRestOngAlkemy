package org.alkemy.somosmas.exception;

public class NewsNotFoundException extends RuntimeException{

    public NewsNotFoundException(String message){
        super(message);
    }
}
