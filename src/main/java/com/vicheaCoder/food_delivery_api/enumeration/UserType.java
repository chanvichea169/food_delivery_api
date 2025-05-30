package com.vicheaCoder.food_delivery_api.enumeration;

public enum UserType {
    ADMIN,
    CUSTOMER,
    MANAGER;

    public static UserType fromString(String type) {
        if (type == null) {
            throw new IllegalArgumentException("User type is null");
        }
        try {
            return UserType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user type: " + type);
        }
    }
}

