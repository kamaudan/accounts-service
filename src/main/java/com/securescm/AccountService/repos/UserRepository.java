package com.securescm.AccountService.repos;

import com.securescm.AccountService.entities.Stakeholder;
import com.securescm.AccountService.entities.Userz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<Userz, Integer> {

   Userz findTop1ByUsername(String userName);
   @Query("SELECT u FROM Userz u LEFT JOIN u.stakeholderStaff sf where sf.staff = ?1")
   List<Userz> findAllByStakeholder(Stakeholder stakeholder);
}

