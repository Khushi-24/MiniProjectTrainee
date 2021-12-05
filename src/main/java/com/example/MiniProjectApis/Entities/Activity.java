package com.example.MiniProjectApis.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Activity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Column
    private String activityName;

    @Column
    private String shortDescription;

    @Column
    private String aboutActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    @Column(name = "propertyId", insertable = false, updatable = false, nullable = false)
    private Long propertyId;

    @JsonIgnore
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ActivityImages> activityImagesSet = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ActivityAmenities> amenitiesActivitySet = new HashSet<>();
}
