package com.edu.test.paginationdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billUid")
    private Long billUid;

    @Column(name = "customerId",insertable = false,nullable = false,updatable=false)
    private Long customerId;

    @Column(name = "billAmount")
    private Long billAmount;

    @Column(name = "billCode")
    private String billCode;

    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "customerId",nullable = false)
    private Customer customer;





}
