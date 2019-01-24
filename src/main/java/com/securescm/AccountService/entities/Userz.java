package com.securescm.AccountService.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users_table" )
 public class Userz implements Serializable {
    @Id
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private  String username;

    @Column(name = "email_address")
    private String email_address;

    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private Userz createdBy;

    @Column(name = "created_at")
    private Date createdAt;


    @Column(name = "stakeholder_id")
    private int stakeholderId;


    @JoinColumn(name = "stakeholder", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stakeholder stakeholder;



    @Column(name = "date_last_updated")
    private Date dateLastUpdated;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "blocked")
    private boolean blocked;


    @Column(name = "deleted_at")
    private Date deletedAt;

}
