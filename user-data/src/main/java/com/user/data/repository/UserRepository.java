package com.user.data.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> findByEmailAddressAndPassword(String emailAddress, String password);
    
    @Modifying
    @Transactional
    @Query(value = "update User u set u.lastLoginDate = now() where u.emailAddress = ?1")
    int setLastLoginTime(String email);
}
