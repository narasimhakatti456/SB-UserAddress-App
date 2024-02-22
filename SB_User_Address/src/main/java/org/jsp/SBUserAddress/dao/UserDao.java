package org.jsp.SBUserAddress.dao;

import java.util.Optional;

import org.jsp.SBUserAddress.dto.User;
import org.jsp.SBUserAddress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> verify(long phone, String password) {
		return userRepository.verify(phone, password);
	}

	public Optional<User> verify(String email, String password) {
		return userRepository.verify(email, password);
	}
}
