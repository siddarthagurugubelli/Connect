package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.LikeException;
import socialMediaApp.requests.LikeRequest;
import socialMediaApp.responses.like.LikeResponse;
import socialMediaApp.services.LikeService;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

	private final LikeService likeService;

	public LikesController(LikeService likeService) {
		this.likeService = likeService;
	}

	// http://localhost:8080/api/likes/add
//    {
//    	"userId":2,
//    	"postId":1
//    	}
	@SecurityRequirement(name = "Authorization")
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody LikeRequest likeRequest) throws LikeException {
		likeService.add(likeRequest);
		return new ResponseEntity<>("Added", HttpStatus.OK);
	}

	// http://localhost:8080/api/likes/getallbypost/{p_id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getallbypost/{postId}")
	public ResponseEntity<List<LikeResponse>> getAllByPost(@PathVariable int postId) throws LikeException {
		return new ResponseEntity<>(likeService.getAllByPost(postId), HttpStatus.OK);
	}

	// http://localhost:8080/api/likes/getallbyuser/{u_id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getallbyuser/{userId}")
	public ResponseEntity<List<LikeResponse>> getAllByUser(@PathVariable int userId) throws LikeException {
		return new ResponseEntity<>(likeService.getAllByUser(userId), HttpStatus.OK);
	}

	// http://localhost:8080/api/likes/isliked?userId= &postId=
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/isliked")
	public ResponseEntity<Boolean> isLiked(@RequestParam int userId, @RequestParam int postId) {
		return new ResponseEntity<>(likeService.isLiked(userId, postId), HttpStatus.OK);
	}

	// http://localhost:8080/api/likes/delete.....userid and postid in body
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/delete/{userId}/{postId}")
	public ResponseEntity<String> delete(@PathVariable int userId, @PathVariable int postId) throws LikeException {
		LikeRequest likeRequest = new LikeRequest();
		likeRequest.setPostId(postId);
		likeRequest.setUserId(userId);
		likeService.delete(likeRequest);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
