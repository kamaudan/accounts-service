package com.securescm.AccountService.repos;

import com.securescm.AccountService.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {


   User findTop1ByUsername(String username);
}

