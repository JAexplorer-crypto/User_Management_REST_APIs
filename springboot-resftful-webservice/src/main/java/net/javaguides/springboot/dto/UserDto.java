package net.javaguides.springboot.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {



    private Long id;

    //User first name should not be null or empty
    @NotEmpty (message = "User firstName should not be null or empty")
    private String firstName;
    //User first name should not be null or empty

    @NotEmpty(message = "User lastName should not be null or empty")

    private String lastName;

    //User first name should not be null or empty
    //email address should be valid
    @NotEmpty (message = "User email should not be null or empty")
@Email (message = "Email address is not valid")
    private String email;

}
