package org.example.rideshare.controller;

import jakarta.validation.Valid;
import org.example.rideshare.dto.CreateRideRequest;
import org.example.rideshare.model.Ride;
import org.example.rideshare.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RideController {
    @Autowired
    private RideService rideService;

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@Valid @RequestBody CreateRideRequest request) {
        Ride ride = rideService.createRide(request, getUsername());
        return ResponseEntity.status(201).body(ride);
    }

    @GetMapping("/user/rides")
    public ResponseEntity<List<Ride>> getUserRides() {
        List<Ride> rides = rideService.getUserRides(getUsername());
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/driver/rides/requests")
    public ResponseEntity<List<Ride>> getPendingRides() {
        List<Ride> rides = rideService.getPendingRides();
        return ResponseEntity.ok(rides);
    }

    @PostMapping("/driver/rides/{rideId}/accept")
    public ResponseEntity<Ride> acceptRide(@PathVariable String rideId) {
        Ride ride = rideService.acceptRide(rideId, getUsername());
        return ResponseEntity.ok(ride);
    }

    @PostMapping("/rides/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable String rideId) {
        Ride ride = rideService.completeRide(rideId);
        return ResponseEntity.ok(ride);
    }
}
