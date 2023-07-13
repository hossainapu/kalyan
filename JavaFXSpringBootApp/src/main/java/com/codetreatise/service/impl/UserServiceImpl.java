package com.codetreatise.service.impl;

import java.util.List;
import java.util.Optional;

import com.codetreatise.common.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.entity.User;
import com.codetreatise.repository.UserRepository;
import com.codetreatise.service.UserService;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public User update(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(userRepository.findById(id).get());
	}

	@Override
	public User find(Long id) {
		Optional<User> user =  userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();

		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean authenticate(String username, String password){

		User user = this.findByUserName(username);
		if(user == null){
			return false;
		}else{
			if(Utils.getMd5(password).equals(user.getPassword())) return true;
			else return false;
		}
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName.trim().toUpperCase());
	}

	@Override
	public void deleteInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}
	
}
