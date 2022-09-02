package org.example.internetshopspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.internetshopspring.enums.ErrorType;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;
    private ErrorType errorType;
}
