package com.vicheaCoder.food_delivery_api.model;


import com.vicheaCoder.food_delivery_api.enumeration.PaymentMethod;
import com.vicheaCoder.food_delivery_api.enumeration.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_payment")
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    @Column(name = "payment_at", updatable = false)
    private Date paymentAt;
    private Long orderId;

}
