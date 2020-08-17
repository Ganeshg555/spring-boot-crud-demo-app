package org.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.crud.model.User;
import org.crud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceImplTest {
	
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Test
	public void saveTest() {
		User user = new User(1L, "John", "123456", "Web");
		when(userRepository.save(user)).thenReturn(user);
		User newUser = userServiceImpl.save(user);
		assertEquals("John", newUser.getUsername());
	}
	
	@Test
	public void getAllTest() {
		when(userRepository.findAll()).thenReturn(Stream.of(
				new User(1L, "John", "123456", "Web"),
				new User(2L, "Harry", "123456", "Web")
				).collect(Collectors.toList()));
		List<User> users = userServiceImpl.getAll();
		assertEquals(2, users.size());
	}
	
	@Test
	public void deleteTest() {
		when(userRepository.deleteById(1L)).thenReturn(1);
		long result = userServiceImpl.delete(1L);
		assertEquals(1, result);
	}

}
