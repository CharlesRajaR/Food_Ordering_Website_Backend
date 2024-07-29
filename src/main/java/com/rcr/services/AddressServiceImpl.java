package com.rcr.services;

import com.rcr.model.Address;
import com.rcr.repository.AddressRepository;
import com.rcr.request.AddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) throws Exception {
        Address createdAddress = addressRepository.save(address);
        return createdAddress;
    }

    @Override
    public List<Address> getAllAddress() throws Exception {
        List<Address> address = addressRepository.findAll();
        return address;
    }
}
