package net.javaguides.springboot.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResouceNotFoundException;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create User REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser( @RequestBody @Valid UserDto user){
        UserDto savedUser=userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //build get user by Id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Build get All users REST API

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
      List<UserDto> users =  userService.getAllUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //build Update User Rest API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updatedUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user){
        user.setId(userId);
       UserDto updatedUser= userService.updateUser(user);
       return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    //build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfullly deleted", HttpStatus.OK);
    }

    //exception only with respect to Controller
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResouceNotFoundException(ResouceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "User Not Found"
        );

                return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleResouceNotFoundException(EmailAlreadyExistsException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Email_Already_Exists"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
