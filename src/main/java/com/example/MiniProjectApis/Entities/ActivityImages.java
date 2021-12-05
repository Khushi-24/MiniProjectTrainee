package com.example.MiniProjectApis.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ActivityImages extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column
    private String imageName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId", referencedColumnName = "activityId")
    private Activity activity;

    @Column(name = "activityId", insertable = false, updatable = false, nullable = false)
    private Long activityId;
}
