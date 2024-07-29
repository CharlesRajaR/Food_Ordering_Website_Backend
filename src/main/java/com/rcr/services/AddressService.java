package com.rcr.services;

import com.rcr.model.Address;
import com.rcr.request.AddressRequest;

import java.util.List;

public interface AddressService {
    public Address createAddress(Address address)throws Exception;
    public List<Address> getAllAddress()throws Exception;
}
