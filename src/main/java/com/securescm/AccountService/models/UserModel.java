package com.securescm.AccountService.models;

import com.securescm.AccountService.entities.Staff;
import com.securescm.AccountService.entities.Stakeholder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
 public class UserModel {
    private int id;
    private int staffId;
    private String firstName;
    private String lastName;
    private long IdNumber;
    private Stakeholder stakeholder;
    private String username;
    private String password;
    private boolean blocked;
    private int loginAttempts;
}
