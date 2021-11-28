package com.example.MiniProjectApis.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Property")
@Getter
@Setter
public class Property extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @Column
    private String propertyName;

    @Column
    private String propertyAddress;

    @Column
    private String propertyArea;

//    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<PropertyImages> propertyImagesSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long userId;
}
