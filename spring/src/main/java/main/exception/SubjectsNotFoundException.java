package main.exception;

public class SubjectsNotFoundException extends RuntimeException {
    public SubjectsNotFoundException(String message){
        super(message);
    }
}