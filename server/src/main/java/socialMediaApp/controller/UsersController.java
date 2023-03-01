package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.UserException;
import socialMediaApp.requests.UserAddRequest;
import socialMediaApp.responses.user.UserResponse;
import socialMediaApp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private final UserService userService;

	public UsersController(UserService userService) {
		this.userService = userService;
	}

	// http://localhost:8080/api/users/getall
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getall")
	public ResponseEntity<List<UserResponse>> getAll() {
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}

	// http://localhost:8080/api/users/getbyid/{id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<UserResponse> getResponseById(@PathVariable int id) throws UserException {
		return new ResponseEntity<>(userService.getResponseById(id), HttpStatus.OK);
	}

	// http://localhost:8080/api/users/isfollowing?userId={id}&followingId={id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/isfollowing")
	public ResponseEntity<Boolean> isFollowing(@RequestParam int userId, @RequestParam int followingId) {
		return new ResponseEntity<>(userService.isFollowing(userId, followingId), HttpStatus.OK);
	}

	// http://localhost:8080/api/users/add
//    @PostMapping("/add")
//    public ResponseEntity<String> add(@RequestBody UserAddRequest userAddRequest) throws UserException{
//        userService.add(userAddRequest);
//        return new ResponseEntity<>("User Added",HttpStatus.CREATED);
//    }

	// http://localhost:8080/api/users/delete/{id}
//	@SecurityRequirement(name = "Authorization")
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<Void> delete(@PathVariable int id) throws UserException {
//		userService.delete(id);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
}
