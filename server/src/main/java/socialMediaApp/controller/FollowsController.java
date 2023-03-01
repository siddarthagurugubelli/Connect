package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.FollowException;
import socialMediaApp.requests.FollowRequest;
import socialMediaApp.services.FollowService;

@RestController
@RequestMapping("/api/follows")
public class FollowsController {

	private final FollowService followService;

	public FollowsController(FollowService followService) {
		this.followService = followService;
	}

	// http://localhost:8080/api/follows/add.....userId and followingId
//    {
//    	"userId":1,
//    	"followingId":3
//    	}
	@SecurityRequirement(name = "Authorization")
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody FollowRequest followRequest) throws FollowException {
		followService.add(followRequest);
		return new ResponseEntity<>("Followed", HttpStatus.OK);
	}

	// http://localhost:8080/api/follows/delete....userId and followingId
//    {
//    	"userId":2,
//    	"followingId":3
//    	}
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/delete/{userId}/{followerId}")
	public ResponseEntity<String> delete(@PathVariable int userId, @PathVariable int followerId)
			throws FollowException {
		FollowRequest followRequest = new FollowRequest();
		followRequest.setUserId(userId);
		followRequest.setFollowingId(followerId);
		followService.delete(followRequest);
		return new ResponseEntity<>("Unfollowed", HttpStatus.OK);
	}
}
