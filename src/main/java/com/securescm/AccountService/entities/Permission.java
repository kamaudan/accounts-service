package com.securescm.AccountService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    public int id;

    @Column(name = "menu_id")
    public int menuId;

    @Column(name = "user_role_id")
    public int userRoleId;

    @Column(name = "view_allowed")
    public int viewAllowed;

    @Column(name = "add_allowed")
    public int addAllowed;

    @Column(name = "edit_allowed")
    public int editAllowed;

    @Column(name = "delete_allowed")
    public int deleteAllowed;

}
