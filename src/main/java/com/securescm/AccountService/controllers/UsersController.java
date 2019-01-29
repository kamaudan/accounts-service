package com.securescm.AccountService.controllers;


import com.securescm.AccountService.Response.Response;
import com.securescm.AccountService.entities.User;
import com.securescm.AccountService.models.Status;
import com.securescm.AccountService.models.UserModel;
import com.securescm.AccountService.models.UserRequest;
import com.securescm.AccountService.repos.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class.getName());

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody User user){
         Status status = createUpdate(null,user);
         return ResponseEntity.ok().body(status);
    }

    @PutMapping("/update")
    public ResponseEntity<Status> update(@RequestBody User user) {
        Status status = createUpdate(null,user);
        return ResponseEntity.ok().body(status);
    }

    private Status createUpdate(Integer id, User entity){
        Status status ;
        User user = new User();

        if(id != null){
            Optional<User> optionalUser =  userRepository.findById(id);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
                user.setFirstName(entity.getFirstName());
                user.setFirstName(entity.getLastName());
                user.setPassword(entity.getPassword());
                user.setDateLastUpdated(new Date());
                userRepository.save(user);
                status = Response.SUCCESS.status();
            }else{
              status = Response.USER_NOT_FOUND.status();
            }
        }else{
            User exists = userRepository.findTop1ByUsername(entity.getUsername());

            if(exists == null) {
                user.setFirstName(entity.getFirstName());
                user.setFirstName(entity.getLastName());
                user.setCreatedAt(new Date());
                user.setUsername(entity.getUsername());
                user.setEmail_address(entity.getEmail_address());
                user.setPassword(entity.getPassword());
                userRepository.save(user);
            }else{
                status = Response.USER_NAME_TAKEN.status();
                status.setMessage(MessageFormat.format(status.getMessage(),entity.getUsername()));
            }
            status = Response.SUCCESS.status();
        }
        return status;
    }


    @GetMapping("update-attempts")
    public ResponseEntity updateAttempts(@RequestBody UserRequest request){
      Optional<User> optionalUser = userRepository.findById(request.getUserId());
      Status status;
      if(optionalUser.isPresent()){
          User user = optionalUser.get();
          user.setLoginAttempts(request.getNoOfAttempts());
          if(request.getNoOfAttempts() >= 5){
              user.setBlocked(true);
              status = Response.ACCOUNT_BLOCKED.status();
          }else {
              user.setLastLogin(new Date());
              user.setIpAddress(request.getIpAddress());
              status = Response.SUCCESS.status();
          }
        userRepository.save(user);

      }else{
          status = Response.USER_NOT_FOUND.status();
      }
      return ResponseEntity.ok(status);


    }
//
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody UserRequest userModel){
        String userName = userModel.getUsername();
        if(userName != null){
            log.info(userName);
            User user  = userRepository.findTop1ByUsername(userName);

            if(user  != null){
                if(user.isBlocked()){
                    return ResponseEntity.badRequest().body(Response.ACCOUNT_BLOCKED.status());
                }else {
                    return ResponseEntity.ok(getModel(user));
                }
            }else{
                return ResponseEntity.badRequest().body(Response.USER_NOT_FOUND.status());
            }
        } else {
            Status status = Response.FIELD_REQUIRED.status();
            status.setMessage(MessageFormat.format(status.getMessage(), "username"));
            return ResponseEntity.badRequest().body(status);
        }
    }

    private UserModel getModel(User user){
        UserModel model = null;

        if(user != null){
            model = new UserModel();
            model.setId(user.getId());
            model.setFirstName(user.getFirstName());
            model.setLastName(user.getLastName());
            model.setUsername(user.getUsername());
            model.setStakeholder(user.getStakeholder());
            model.setPassword(user.getPassword());
            model.setBlocked(user.isBlocked());
            model.setLoginAttempts(user.getLoginAttempts() != null ? user.getLoginAttempts() : 0);
        }
        return model;
    }
}





