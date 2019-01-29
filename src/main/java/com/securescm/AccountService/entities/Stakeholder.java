package com.securescm.AccountService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity()
@Table(name = "stakeholder")
public class Stakeholder  implements Serializable {
    @Id
    private int id;
    private String name;

    @JoinColumn(name = "stakeholder_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StakeholderType StakeholderType;
}
