package com.vicheaCoder.food_delivery_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vicheaCoder.food_delivery_api.enumeration.UserType;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty(value = "first_name")
    private String firstname;
    @JsonProperty(value = "last_name")
    private String lastname;
    private String gender;
    @JsonProperty(value = "date_of_birth")
    private String dateOfBirth;
    private String email;
    @JsonProperty(value = "phone_number")
    private String phoneNumber;
    private String address;
    @Column(name = "user_type")
    private String userType;
    private String status;
    @JsonProperty(value = "created_at")
    private Date createdAt;
    @JsonProperty(value = "updated_at")
    private Date updatedAt;
    @JsonProperty(value = "created_by")
    private String createdBy;
    @JsonProperty(value = "updated_by")
    private String updatedBy;
}
