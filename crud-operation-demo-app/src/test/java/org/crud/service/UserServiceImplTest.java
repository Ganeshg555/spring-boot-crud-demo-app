package org.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.crud.exceptions.UserIdNotFoundException;
import org.crud.model.User;
import org.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetById() {
		Optional<User> user = Optional.of(new User(1, "John", "123456", "Test"));
		Mockito.when(userRepository.findById(1L)).thenReturn(user);
		User newUser = userServiceImpl.getById(1);
		Mockito.verify(userRepository).findById(1L);
		assertEquals(1, newUser.getId());
	}
	
	@Test
	void testFindAll() {
		when(userRepository.findAll()).thenReturn(Stream.of(
				new User(1, "John", "123456", "Test"),
				new User(2, "Henry", "123456", "Test"),
				new User(3, "Harry", "123456", "Test"),
				new User(4, "Parker", "123456", "Test")
				).collect(Collectors.toList()));
		List<User> userList = userServiceImpl.getAll();
		assertEquals(4, userList.size());
	}
	
	@Test
	void testSave() {
		User user = new User(1, "John", "123456", "Test");
		when(userRepository.save(user)).thenReturn(user);
		User newUser = userServiceImpl.save(user);
		assertEquals(1, newUser.getId());
	}
	
	@Test
	void testDelete() {
		when(userRepository.deleteById(1)).thenReturn(1);
		long rows = userServiceImpl.delete(1);
		assertEquals(1, rows);
	}
	
	@Test
	void testUpdate() throws UserIdNotFoundException {
		User user = new User(1, "John", "123456", "Test");
		Optional<User> oUser = Optional.of(user);
		Mockito.when(userRepository.findById(1L)).thenReturn(oUser);
		when(userRepository.save(user)).thenReturn(user);
		User newUser = userServiceImpl.update(user);
		assertEquals(1, newUser.getId());
	}
	
	@Test
	void testSaveAll() {
		List<User> userList = Stream.of(
				new User(1, "John", "123456", "Test"),
				new User(2, "Henry", "123456", "Test"),
				new User(3, "Harry", "123456", "Test"),
				new User(4, "Parker", "123456", "Test")
				).collect(Collectors.toList());
		when(userRepository.saveAll(userList)).thenReturn(userList);
		List<User> newUserList = userServiceImpl.saveAll(userList);
		assertEquals(4, newUserList.size());
	}
	
}
