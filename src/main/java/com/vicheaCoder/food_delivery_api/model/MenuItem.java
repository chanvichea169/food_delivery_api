package com.vicheaCoder.food_delivery_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tbl_menu_item",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code"}),
        }
)
public class MenuItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Integer availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
