package org.jsp.SBUserAddress.repository;

import java.util.Optional;

import org.jsp.SBUserAddress.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("select u.address from User u where u.email=?1 and u.password=?2")
	public Optional<Address> verify(String email, String password);

	public Optional<Address> findByCountry(String country);
}