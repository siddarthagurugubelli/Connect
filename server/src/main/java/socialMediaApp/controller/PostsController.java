package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.PostException;
import socialMediaApp.requests.PostAddRequest;
import socialMediaApp.responses.post.PostGetResponse;
import socialMediaApp.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
	private final PostService postService;

	public PostsController(PostService postService) {
		this.postService = postService;
	}

	// http://localhost:8080/api/posts/getall
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getall")
	public ResponseEntity<List<PostGetResponse>> getAll() {
		return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
	}

	// http://localhost:8080/api/posts/getbyid/{id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<PostGetResponse> getById(@PathVariable int id) throws PostException {
		return new ResponseEntity<>(postService.getResponseById(id), HttpStatus.OK);
	}

	// http://localhost:8080/api/posts/getallbyuser/{id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getallbyuser/{userId}")
	public ResponseEntity<List<PostGetResponse>> getAllByUser(@PathVariable int userId) throws PostException {
		return new ResponseEntity<>(postService.getAllByUser(userId), HttpStatus.OK);
	}

	// http://localhost:8080/api/posts/getbyuserfollowing/{id}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getbyuserfollowing/{userId}")
	public ResponseEntity<List<PostGetResponse>> getAllByUserFollowing(@PathVariable int userId) {
		return new ResponseEntity<>(postService.getByUserFollowing(userId), HttpStatus.OK);
	}

// http://localhost:8080/api/posts/add
//    {
//    	"userId":1,
//    	"description":"My first post"
//    	}
	@SecurityRequirement(name = "Authorization")
	@PostMapping("/add")
	public ResponseEntity<Integer> add(@RequestBody PostAddRequest postAddRequest) throws PostException {
		int postId = postService.add(postAddRequest);
		return new ResponseEntity<>(postId, HttpStatus.CREATED);
	}

	// http://localhost:8080/api/posts/delete/4
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("postId") int postId) throws PostException {
		postService.deletePost(postId);
		return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
	}

}
