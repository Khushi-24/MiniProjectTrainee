package com.example.MiniProjectApis.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ActivityAmenities extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityActivityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Amenities amenities;

    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @Column(name = "activityId", insertable = false, updatable = false, nullable = false)
    private Long activityId;
}
