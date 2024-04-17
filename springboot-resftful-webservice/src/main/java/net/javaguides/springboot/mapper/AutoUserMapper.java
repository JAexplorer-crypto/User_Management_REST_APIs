package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

//    mapperConstruct provides its implementation at run time
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

//    if the field entities in UserDto and User are different then we use @Mapping annotation to converge them ie.
//@Mapping(source = "email", target = "emailAdrress")


    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
