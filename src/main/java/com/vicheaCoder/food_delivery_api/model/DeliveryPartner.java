package com.vicheaCoder.food_delivery_api.model;

import com.vicheaCoder.food_delivery_api.enumeration.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_delivery_partner")
public class DeliveryPartner extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String firstname;
    private String lastname;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    private String password;
    private String gender;
    private Date dateOfBirth;
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;
    private String email;
    private String address;
    private VehicleType vehicleType;
    private boolean available;

}
