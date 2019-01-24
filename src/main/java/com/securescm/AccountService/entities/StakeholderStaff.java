package com.securescm.AccountService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stakeholder_staff")
public class StakeholderStaff {

    @Id
    public int id;

    @JoinColumn(name = "stakeholder", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Stakeholder stakeholder;

    @JoinColumn(name = "staff", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Staff staff;

}
