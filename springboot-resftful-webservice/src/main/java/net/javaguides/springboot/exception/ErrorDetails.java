package net.javaguides.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private LocalDateTime localDateTime; // timestamp
    private String message; // Error message
    private String path; // api/users
    private String errorCode; //status code
}
