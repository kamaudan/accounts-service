package com.securescm.AccountService.models;

import com.securescm.AccountService.entities.Permission;
import com.securescm.AccountService.entities.Stakeholder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
 public class UserModel implements Serializable {


    private static final long serialVersionUID = -3622386171997650688L;

    private int id;
    private String firstName;
    private String lastName;
    private Stakeholder stakeholder;
    private String username;
    private String password;
    private boolean blocked;
    private int loginAttempts;
    private Permission permissions;
}
