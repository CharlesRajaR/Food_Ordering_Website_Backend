package com.rcr.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateEventRequest {

    private String name;
    private String image;
    private String location;
    private String startedAt;
    private String endedAt;
}
