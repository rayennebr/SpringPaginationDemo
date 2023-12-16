package com.edu.test.paginationdemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "city")
public class City  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cityUid")
    private Long cityUid;

    // Remove this @Column annotation
    @Column(name = "countryId",insertable=false, updatable=false)
    private Long countryId;

    @Column(name = "cityCod")
    private String cityCod;

    @Column(name = "cityDesc")
    private String cityDesc;

    @ManyToOne
    @JoinColumn(name = "countryId", referencedColumnName = "countryId", nullable = false, insertable = false)
    private Country country;
}
