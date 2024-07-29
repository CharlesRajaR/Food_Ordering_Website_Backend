package com.rcr.services;

import com.rcr.model.Events;
import com.rcr.model.Restaurant;
import com.rcr.repository.EventRepository;
import com.rcr.request.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Override
    public Events createEvent(CreateEventRequest req, Long restaurantId) throws Exception {
        Events event = new Events();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        event.setName(req.getName());
        event.setImage(req.getImage());
        event.setLocation(req.getImage());
        event.setStartedAt(req.getStartedAt());
        event.setEndedAt(req.getEndedAt());
        event.setRestaurant(restaurant);
        Events createdEvent = eventRepository.save(event);

        return createdEvent;
    }

    @Override
    public List<Events> findEventByRestaurantId(Long restaurantId) throws Exception {
        List<Events> events = eventRepository.findByRestaurantId(restaurantId);

        return events;
    }

    @Override
    public List<Events> findAllEvents() throws Exception {
        List<Events> events = eventRepository.findAll();
        return events;
    }

    @Override
    public String deleteEvent(Long eventId) throws Exception {
        Optional<Events> event = eventRepository.findById(eventId);
        eventRepository.deleteById(eventId);

        return "event deleted successfully";
    }
}
