package com.team5.fandom.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.team5.fandom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


    boolean existsByUserName(String userName);


}
