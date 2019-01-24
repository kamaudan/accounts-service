package com.securescm.AccountService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity()
@Table(name = "stakeholder")
public class Stakeholder {
    @Id
    public int id;
    @Column(name = "user_id")
    public String userId;

    @Column(name = "name")
    public String name;

    @Column(name = "logo_id")
    public String logoId;

    @Column(name = "type_id")
    public String typeId;

    @JoinColumn(name = "stakeholder_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public StakeholderType StakeholderType;
}
