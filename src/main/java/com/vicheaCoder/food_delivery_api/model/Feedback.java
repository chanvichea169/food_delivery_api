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
@Table(name = "tbl_feedback")
public class Feedback extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String comment;
    private Double rating;
    @Column(name = "feedback_date")
    private Date feedbackDate;
    private Long userId;
    private Long restaurantId;
    private Long orderId;
    private Long deliveryId;

}
