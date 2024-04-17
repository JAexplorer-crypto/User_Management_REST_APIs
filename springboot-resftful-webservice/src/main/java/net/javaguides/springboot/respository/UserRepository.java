package net.javaguides.springboot.respository;

import net.javaguides.springboot.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
