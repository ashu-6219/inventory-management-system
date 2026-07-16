package com.ashutosh.inventory.dto;

// Creates a constructor with all fields.
import lombok.AllArgsConstructor; 

// 
import lombok.Builder;

/*This is a shortcut.
Instead of writing
getters
setters
toString()
equals()
hashCode()
Lombok generates everything.
 */
import lombok.Data;

// Creates a constructor with no fields.
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

// Genereic <T> is used to make the class reusable for any type of data.
public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;
}
