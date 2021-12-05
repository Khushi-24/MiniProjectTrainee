package com.example.MiniProjectApis.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PropertyImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    @Column
    private String imageName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    @Column(name = "propertyId", insertable = false, updatable = false, nullable = false)
    private Long propertyId;
}