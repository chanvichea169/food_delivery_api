package com.vicheaCoder.food_delivery_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_restaurant")
public class Restaurant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String code;
    private String name;
    private String category;
    private String description;
    private double rating;
    private String address;
    private String phoneNumber;
    private String logUrl;
    @Temporal(TemporalType.TIME)
    private Date openTime;
    @Temporal(TemporalType.TIME)
    private Date closeTime;
}
