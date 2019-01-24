package com.securescm.AccountService.repos;

import com.securescm.AccountService.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role, Integer> {
}
