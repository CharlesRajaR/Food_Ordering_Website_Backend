package com.rcr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rcr.model.Address;
public interface AddressRepository extends JpaRepository<Address, Long> {
}
