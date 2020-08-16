package org.crud.service;

import java.util.List;

import org.crud.exceptions.UserIdNotFoundException;
import org.crud.model.User;

public interface UserService {
	List<User> getAll();
	User getById(long id);
	User save(User user);
	User update(User user) throws UserIdNotFoundException;
	long delete(long id);
	List<User> saveAll(List<User> users);
}
