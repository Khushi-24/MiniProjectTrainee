package com.example.MiniProjectApis.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "PropertyImages")
@Getter
@Setter
public class PropertyImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    @Column(name = "propertyId", insertable = false, updatable = false, nullable = false)
    private Long propertyId;
}
