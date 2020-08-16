package org.crud.controller;

import java.util.List;

import org.crud.api.response.AppResponse;
import org.crud.exceptions.UserNotFoundException;
import org.crud.model.User;
import org.crud.service.UserService;
import org.crud.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", this.userService.getAll()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) throws UserNotFoundException {
		User user = this.userService.getById(id);
		if(user != null)
			return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", user));
		else throw new UserNotFoundException("User details not found");
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<?> save(@RequestBody User user) {
		return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", this.userService.save(user)));
	}
	
	@PutMapping(consumes="application/json")
	public ResponseEntity<?> update(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", this.userService.update(user)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
		long result = this.userService.delete(id);
		if(result > 0)
			return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", null));
		else
			return ResponseEntity.ok(new AppResponse(HttpStatus.NO_CONTENT, "Failed", null));
	}
	
	@PostMapping("/import")
	public ResponseEntity<?> importData() throws Exception {
		List<User> users = this.fileUtil.loadUsersData();
		if(users.isEmpty()) {
			throw new UserNotFoundException("Failed to import user data");
		}
		return ResponseEntity.ok(new AppResponse(HttpStatus.OK, "Success", this.userService.saveAll(users)));
	}

}
