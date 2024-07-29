package com.rcr.request;

import lombok.Data;

import java.util.List;

@Data
public class AddressRequest {
    private List<String> deliveryAddress;

}
