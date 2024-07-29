package com.rcr.request;

import com.rcr.model.Address;
import com.rcr.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private AddressRequest address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
