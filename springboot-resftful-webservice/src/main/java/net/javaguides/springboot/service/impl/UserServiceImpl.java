package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResouceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.respository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public ModelMapper modelMapper;

    private UserRepository userRepository;



    //convert USerDto to User JPA entity
    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already exists for the user");
        }
//        User user1 = UserMapper.mapToUser(userDto);

//        using modelMapper Library to convert User DTO into User

//        User user1 = modelMapper.map(userDto, User.class);

//        Using mapconstruct
        User user1 = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user1);
       //convert User JPA entity into UserDto
        //        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);


//        using model Mapper Library to convert User into UserDto
//        UserDto savedUserDto = modelMapper.map(user1,UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(user1);



        return savedUserDto;
    }



    @Override
    public UserDto getUserById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(()->new ResouceNotFoundException("User", "id",userId));
        User user = optionalUser;

//        return UserMapper.mapToUserDto(user);

//        Using modelMapper library to convert User into UserDto
//return modelMapper.map(user,UserDto.class);

//        Using mapConstruct
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
// since this response is coming from the database hence it will return a JPA Entity
        List<User> userList = userRepository.findAll();

//        return userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

//        Using modelMapper to convert user into UserDto

//        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    return userList.stream().map(user -> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
       User existingUser= userRepository.findById(user.getId()).orElseThrow(()->new ResouceNotFoundException("User","id",user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser=userRepository.save(existingUser);
//        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser)

//      Using modelMapper library to convert user into UserDto

//UserDto updatedUserDto = modelMapper.map(existingUser,UserDto.class);
        UserDto updatedUserDto = AutoUserMapper.MAPPER.mapToUserDto(existingUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResouceNotFoundException("User","id",userId));

        userRepository.deleteById(existingUser.getId());
    }


}