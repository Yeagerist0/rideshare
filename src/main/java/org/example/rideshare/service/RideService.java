package org.example.rideshare.service;

import org.example.rideshare.dto.CreateRideRequest;
import org.example.rideshare.exception.BadRequestException;
import org.example.rideshare.exception.NotFoundException;
import org.example.rideshare.model.Ride;
import org.example.rideshare.model.User;
import org.example.rideshare.repository.RideRepository;
import org.example.rideshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Ride createRide(CreateRideRequest request, String username) {
        User user = getUserByUsername(username);
        if (!"ROLE_USER".equals(user.getRole())) {
            throw new BadRequestException("Only users can request rides");
        }
        Ride ride = Ride.builder()
                .userId(user.getId())
                .driverId(null)
                .pickupLocation(request.getPickupLocation())
                .dropLocation(request.getDropLocation())
                .status("REQUESTED")
                .createdAt(new Date())
                .build();
        return rideRepository.save(ride);
    }

    public List<Ride> getUserRides(String username) {
        User user = getUserByUsername(username);
        return rideRepository.findByUserId(user.getId());
    }

    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }

    public Ride acceptRide(String rideId, String username) {
        User driver = getUserByUsername(username);
        if (!"ROLE_DRIVER".equals(driver.getRole())) {
            throw new BadRequestException("Only drivers can accept rides");
        }
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException("Ride not found"));
        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride is not in REQUESTED status");
        }
        ride.setDriverId(driver.getId());
        ride.setStatus("ACCEPTED");
        return rideRepository.save(ride);
    }

    public Ride completeRide(String rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new NotFoundException("Ride not found"));
        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new BadRequestException("Ride is not in ACCEPTED status");
        }
        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }
}
