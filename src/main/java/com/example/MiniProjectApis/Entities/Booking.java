package com.example.MiniProjectApis.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Booking extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column
    private String date;

    @Column
    private Long noOfPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @Column(name = "activityId", insertable = false, updatable = false, nullable = false)
    private Long activityId;

    @Column(name = "userId", insertable = false, updatable = false, nullable = false)
    private Long userId;
}
