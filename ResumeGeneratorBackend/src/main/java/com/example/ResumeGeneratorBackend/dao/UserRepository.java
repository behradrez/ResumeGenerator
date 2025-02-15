package com.example.ResumeGeneratorBackend.dao;


import com.example.ResumeGeneratorBackend.model.UserAccount;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserAccount, Integer> {
    public boolean existsByUsername(String username);
    public UserAccount findByUsername(String username);

    @Query("select u from UserAccount u where u.creationDate <= :comparisonDate")
    public List<UserAccount> findAllUsersOlderThanDate(@Param("comparisonDate") Date timestamp);
}
