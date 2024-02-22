package org.jsp.SBUserAddress.controller;

import org.jsp.SBUserAddress.dto.Address;
import org.jsp.SBUserAddress.dto.ResponseStructure;
import org.jsp.SBUserAddress.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	@Autowired
	private AddressService addressService;

	@PostMapping("/{user_id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address,
			@PathVariable(name = "user_id") int user_id) {
		return addressService.saveAddress(address, user_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> UpdateAddress(@RequestBody Address address) {
		return addressService.updateAddress(address);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable(name = "id") int id) {
		return addressService.findById(id);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Address>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return addressService.verify(email, password);
	}

	@GetMapping(value = "/find-by-country")
	public ResponseEntity<ResponseStructure<Address>> findByCountry(@RequestParam String country) {
		return addressService.findByCountry(country);
	}
}