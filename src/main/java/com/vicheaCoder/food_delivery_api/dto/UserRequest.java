package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicheaCoder.food_delivery_api.enumeration.UserType;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty(value = "first_name")
    private String firstname;
    @JsonProperty(value = "last_name")
    private String lastname;
    private String gender;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    private String address;
    @Column(name = "user_type")
    private String userType;
    private String status;
    @JsonProperty("device_info")
    private DeviceRequest deviceRequest;

}
