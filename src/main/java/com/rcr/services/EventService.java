package com.rcr.services;

import com.rcr.model.Events;
import com.rcr.request.CreateEventRequest;

import java.util.List;

public interface EventService {
    public Events createEvent(CreateEventRequest req, Long restaurantId)throws Exception;
    public List<Events> findEventByRestaurantId(Long restaurantId)throws Exception;
    public List<Events> findAllEvents()throws Exception;
    public String deleteEvent(Long eventId)throws Exception;
}
