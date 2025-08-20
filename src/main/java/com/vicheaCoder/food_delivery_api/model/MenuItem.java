package com.vicheaCoder.food_delivery_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemPhoto> menuItemPhotos = new ArrayList<>();

    public void addPhoto(MenuItemPhoto photo) {
        photo.setMenuItem(this);
        this.menuItemPhotos.add(photo);
    }
}
