package com.securescm.AccountService.controllers;


import com.securescm.AccountService.Response.Response;
import com.securescm.AccountService.entities.Userz;
import com.securescm.AccountService.models.Status;
import com.securescm.AccountService.models.UserModel;
import com.securescm.AccountService.models.UserRequest;
import com.securescm.AccountService.repos.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepository userRepository;

    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Userz user){
         Status status = createUpdate(null,user);
         return ResponseEntity.ok().body(status);
    }

    @PutMapping("/update")
    public ResponseEntity<Status> update(@RequestBody Userz user) {
        Status status = createUpdate(null,user);
        return ResponseEntity.ok().body(status);
    }

    private Status createUpdate(Integer id, Userz entity){
        Status status ;
        Userz user = new Userz();

        if(id != null){
            Optional<Userz> optionalUser =  userRepository.findById(id);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
                user.setStakeholder(entity.getStakeholder());
                user.setPassword(entity.getPassword());
                user.setDateLastUpdated(new Date());
                userRepository.save(user);
                status = Response.SUCCESS.status();
            }else{
              status = Response.USER_NOT_FOUND.status();
            }
        }else{
            Userz exists = userRepository.findTop1ByUsername(entity.getUsername());
            if(exists == null) {
                user.setCreatedBy(entity.getCreatedBy());
                user.setCreatedAt(new Date());
                user.setStakeholder(entity.getStakeholder());
                user.setUsername(entity.getUsername());
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
      Optional<Userz> optionalUser = userRepository.findById(request.getUserId());
      Status status;
      if(optionalUser.isPresent()){
          Userz user = optionalUser.get();
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
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequest userModel){
        String userName = userModel.getUsername();
        if(userName != null){
            Userz user  = userRepository.findTop1ByUsername(userName);
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

    private UserModel getModel(Userz user){
        UserModel model = null;

        /*if(user != null){
            model = new UserModel();
            model.setId(user.getId());
            model.setFirstName(user.getFirstname());
            model.setLastName(user.getStakeholderStaff().getStaff().getLastname());
            model.setStaffId(user.getStakeholderStaff().getStaff().getId());
            model.setIdNumber(user.getStakeholderStaff().getStaff().getCitizenId());
            model.setStakeholder(user.getStakeholderStaff().getStakeholder());

            if(user.getStakeholderStaff() != null) {

            }
            model.setPassword(user.getPassword());
            model.setBlocked(user.isBlocked());
            model.setLoginAttempts(user.getLoginAttempts());
        }*/
        return model;
    }
}





