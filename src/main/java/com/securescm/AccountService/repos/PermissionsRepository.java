package com.securescm.AccountService.repos;

import com.securescm.AccountService.entities.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionsRepository extends CrudRepository <Permission, Integer> {
}
