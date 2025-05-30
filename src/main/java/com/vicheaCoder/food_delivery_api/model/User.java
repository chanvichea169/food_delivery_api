package com.vicheaCoder.food_delivery_api.model;


import com.vicheaCoder.food_delivery_api.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String firstname;
    private String lastname;

    @Column(name = "gender", length = 10)
    private String gender;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    @Column(name = "user_type")
    private UserType userType;
    private String status;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> devices;

}
