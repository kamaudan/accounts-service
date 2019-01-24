package com.securescm.AccountService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue
    public int id;

    @JoinColumn(name = "stakeholder_staff", referencedColumnName = "id")
    @ManyToOne(optional = false)
    public Staff StakeholderStaff;

    @Column(name = "first_name")
    public String firstname;

    @Column(name = "last_name")
    public String lastname;

    @Column(name = "job_title _id")
    public int jobTitleId;

    @Column(name = "citizen_id")
    public int citizenId;

    @Column(name = "title_of_courtersy")
    public String titleOfCourtersy;

    @Column(name = "address_id")
    public String addressId;

    @Column(name = "date_of_birth")
    public Date dateOfBirth;

    @Column(name = "hire_date")
    public Date hireDate;

    @Column(name = "staff_notes")
    public String staffNotes;

    @Column(name = "staff_report_id")
    public int staffReportId;

    @Column(name = "photo_id")
    public int photoId;

    @Column(name = "staff_status_id")
    public String staffStatusId;

}
