package org.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.crud.exceptions.UserIdNotFoundException;
import org.crud.model.User;
import org.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Transactional
	@Override
	public User getById(long id) {
		// TODO Auto-generated method stub
		return this.userRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}

	@Transactional
	@Override
	public long delete(long id) {
		// TODO Auto-generated method stub
		return this.userRepository.deleteById(id);
	}

	@Override
	public User update(User user) throws UserIdNotFoundException {
		// TODO Auto-generated method stub
		if(user == null || user.getId() == 0L) {
			throw new UserIdNotFoundException("Invalid User Details");
		}
		Optional<User> exUser = this.userRepository.findById(user.getId());
		if(exUser.isPresent()) {
			return this.userRepository.save(user);
		} else throw new UserIdNotFoundException("Invalid User Details");
	}

	@Override
	public List<User> saveAll(List<User> users) {
		// TODO Auto-generated method stub
		return this.userRepository.saveAll(users);
	}

}
