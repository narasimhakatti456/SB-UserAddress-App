package org.jsp.SBUserAddress.service;

import java.util.Optional;

import org.jsp.SBUserAddress.dao.UserDao;
import org.jsp.SBUserAddress.dto.ResponseStructure;
import org.jsp.SBUserAddress.dto.User;
import org.jsp.SBUserAddress.exception.IdNotFoundException;
import org.jsp.SBUserAddress.exception.InvalidCredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setMessage("User Saved");
		structure.setData(userDao.saveUser(user));
		structure.setStatuscode(HttpStatus.ACCEPTED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		Optional<User> recUser = userDao.findById(user.getId());
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(user.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setAddress(user.getAddress());
			dbUser.setAge(user.getAge());
			dbUser.setGender(user.getGender());
			dbUser.setPassword(user.getPassword());
			dbUser.setPhone(user.getPhone());
			structure.setMessage("User Updated");
			structure.setData(userDao.saveUser(user));
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("User Found");
			structure.setData(recUser.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> verify(long phone, String password) {
		Optional<User> recUser = userDao.verify(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("Verification Successfull");
			structure.setData(recUser.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid Phone or Password");
	}

	public ResponseEntity<ResponseStructure<User>> verify(String email, String password) {
		Optional<User> recUser = userDao.verify(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("Verification Successfull");
			structure.setData(recUser.get());
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialException("Invalid Email or Password");
	}

}