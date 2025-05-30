package com.vicheaCoder.food_delivery_api.respository;

import com.vicheaCoder.food_delivery_api.model.MenuItemPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemPhotoRepository extends JpaRepository<MenuItemPhoto, Long> {
}
