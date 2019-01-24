package com.securescm.AccountService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {
    private String username;
    private Integer userId;
    private Integer noOfAttempts;
    private String ipAddress;

}
