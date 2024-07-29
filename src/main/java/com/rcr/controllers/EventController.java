package com.rcr.controllers;

import com.rcr.model.Events;
import com.rcr.model.User;
import com.rcr.request.CreateEventRequest;
import com.rcr.services.EventService;
import com.rcr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @PostMapping("/admin/event/{restaurantId}")
    public ResponseEntity<Events> createEvent(@RequestBody CreateEventRequest req,
                                              @PathVariable Long restaurantId,
                                              @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        Events event = eventService.createEvent(req, restaurantId);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/admin/events/restaurant/{id}")
    public ResponseEntity<List<Events>> getRestaurantEvents(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Events> events = eventService.findEventByRestaurantId(id);

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents(@RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        List<Events> events = eventService.findAllEvents();

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @DeleteMapping("/admin/event/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId,
                                              @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserByJwToken(jwt);
        String message = eventService.deleteEvent(eventId);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
