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
 public class User implements Serializable {


 private static final long serialVersionUID = -5988896537925725065L;
 @Id
    @GeneratedValue(generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName ="user_sequence", initialValue =  10)
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
    @ManyToOne(optional = false)
    private User createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @JoinColumn(name = "stakeholder", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stakeholder stakeholder;

    @Column(name = "last_updated_at")
    private Date dateLastUpdated;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "login_attempts")
    private Integer loginAttempts;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "deleted_at")
    private Date deletedAt;

}
