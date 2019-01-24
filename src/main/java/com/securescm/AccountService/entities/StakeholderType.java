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
@Table(name = "stakeholder_type")
public class StakeholderType {

    @Id
    public int id;

    @Column(name = "name")
    public String name;
}
