package com.vicheaCoder.food_delivery_api.model;

import com.vicheaCoder.food_delivery_api.enumeration.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_delivery")
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.TIME)
    private Date pickupTime;
    @Temporal(TemporalType.TIME)
    private Date deliveryTime;
    private String pickupAddress;
    private String deliveryAddress;
    private double deliveryFee;
    private DeliveryStatus deliveryStatus;
    private Long deliveryPartnerId;
    private Long orderId;

}
