package com.ashutosh.inventory.exception;

// Spring automatically propagates runtime exceptions. 
// We don't need to declare them with throws everywhere.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {

        // The RuntimeException class already stores the error message.
        // Instead of creating another variable, we simply pass it to the parent class.
        super(message);
    }

}
