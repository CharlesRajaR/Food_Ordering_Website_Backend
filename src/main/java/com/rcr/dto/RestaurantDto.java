package com.rcr.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Embeddable
public class RestaurantDto{
    private Long id;
    private String title;
    @Column(length = 1000)
    private List<String> images;

    private String description;

}
