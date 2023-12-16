package com.edu.test.paginationdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerId")
    private Long customerId;

    @Column(name = "countryId")
    private Long countryId;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "activity")
    private String activity;

    @Column(name = "gender")
    private String gender;

    @OneToOne
    @JoinColumn(name = "countryId" , referencedColumnName = "countryId", insertable = false , updatable = false)
    private Country country;



}
