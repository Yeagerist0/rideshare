package org.example.rideshare.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateRideRequest {
    @NotBlank(message = "Pickup is required")
    private String pickupLocation;

    @NotBlank(message = "Drop is required")
    private String dropLocation;
}
