package com.rcr.repository;

import com.rcr.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {
    public List<Events> findByRestaurantId(Long restaurantId);
}
