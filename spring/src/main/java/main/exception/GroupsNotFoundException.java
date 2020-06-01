package main.exception;

public class GroupsNotFoundException extends RuntimeException {
    public GroupsNotFoundException(String message){
        super(message);
    }
}