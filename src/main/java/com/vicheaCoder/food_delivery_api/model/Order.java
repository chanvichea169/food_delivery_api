package com.vicheaCoder.food_delivery_api.model;

import com.vicheaCoder.food_delivery_api.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_Order")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();
    private double totalAmount;
    private double deliveryFee;
    private double tax;
    private double rating;
    private OrderStatus orderStatus;
    private Long userId;
    private Long restaurantId;
    private Long deliveryId;
    private Long deliveryPartnerId;
    private Long paymentId;
    private Long orderItemId;
}
