package com.vicheaCoder.food_delivery_api.model;

import com.vicheaCoder.food_delivery_api.enumeration.NotificationChannel;
import com.vicheaCoder.food_delivery_api.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_notification")
public class Notification extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String content;
    private NotificationType notificationType;
    private NotificationChannel notificationChannel;
    private boolean read;
    private Long userId;
    private Long deviceId;
}
