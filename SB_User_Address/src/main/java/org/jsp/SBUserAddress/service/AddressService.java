package org.jsp.SBUserAddress.service;

import java.util.Optional;

import org.jsp.SBUserAddress.dao.AddressDao;
import org.jsp.SBUserAddress.dao.UserDao;
import org.jsp.SBUserAddress.dto.Address;
import org.jsp.SBUserAddress.dto.ResponseStructure;
import org.jsp.SBUserAddress.dto.User;
import org.jsp.SBUserAddress.exception.IdNotFoundException;
import org.jsp.SBUserAddress.exception.InvalidCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User user = recUser.get();
			user.getAddress().add(address);
			address.setUser(user);
			structure.setMessage("Address Saved");
			structure.setData(addressDao.saveAddress(address));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
		Optional<Address> recAddress = addressDao.findById(address.getId());
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recAddress.isPresent()) {
			Address dbAddress = recAddress.get();
			dbAddress.setType(address.getType());
			dbAddress.setBuilding_name(address.getBuilding_name());
			dbAddress.setLandmark(address.getLandmark());
			dbAddress.setArea(address.getArea());
			dbAddress.setCity(address.getCity());
			dbAddress.setState(address.getState());
			dbAddress.setCountry(address.getCountry());
			dbAddress.setPincode(address.getPincode());
			structure.setMessage("Address Updated");
			structure.setData(addressDao.saveAddress(address));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {
		Optional<Address> recAddress = addressDao.findById(id);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recAddress.isPresent()) {
			structure.setMessage("Address Found");
			structure.setData(recAddress.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Address>> verify(String email, String password) {
		Optional<Address> recAddress = addressDao.verify(email, password);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (recAddress.isPresent()) {
			structure.setMessage("Verification Successfull");
			structure.setData(recAddress.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid Email or Password");
	}

	public ResponseEntity<ResponseStructure<Address>> findByCountry(String country) {
		Optional<Address> resAddress = addressDao.findByCountry(country);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		if (resAddress.isPresent()) {
			structure.setMessage("Address Found");
			structure.setData(resAddress.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Country Not Found");
	}
}