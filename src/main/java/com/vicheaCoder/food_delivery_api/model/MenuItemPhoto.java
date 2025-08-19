package com.vicheaCoder.food_delivery_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_menu_item_photo")
public class MenuItemPhoto extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String fileType;
    private String fileFormat;
    private String fileSize;
    private String fileName;
    private String smallUrl;
    private String mediumUrl;
    private String largeUrl;
    private String uploadBy;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

}
