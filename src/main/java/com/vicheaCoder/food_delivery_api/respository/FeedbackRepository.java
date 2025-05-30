package com.vicheaCoder.food_delivery_api.respository;


import com.vicheaCoder.food_delivery_api.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
