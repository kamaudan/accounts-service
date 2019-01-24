package com.securescm.AccountService.Response;


import com.securescm.AccountService.models.Status;


public enum  Response {

    SUCCESS(0, "Success"),
    USER_NOT_FOUND(6, "Userz not found"),
    USER_NAME_TAKEN(6, "Username {0} has been taken"),
    ACCOUNT_BLOCKED(6, "Account is blocked"),
    FIELD_REQUIRED(6, "Field {0} is required"),
    NOUSERS(8, "There are no users in the database yet");

    private Status status;
    Response(final int code, final String message) {
        status = new Status(code,message);
    }

    public Status status(){
        return status;
    }
}
