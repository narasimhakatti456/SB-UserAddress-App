package org.jsp.SBUserAddress.dao;

import java.util.Optional;

import org.jsp.SBUserAddress.dto.Address;
import org.jsp.SBUserAddress.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepository addressRepository;

	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	public Optional<Address> findById(int id) {
		return addressRepository.findById(id);
	}

	public Optional<Address> verify(String email, String password) {
		return addressRepository.verify(email, password);
	}

	public Optional<Address> findByCountry(String country) {
		return addressRepository.findByCountry(country);
	}
}
