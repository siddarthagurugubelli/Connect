package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.CommentException;
import socialMediaApp.requests.CommentAddRequest;
import socialMediaApp.responses.comment.CommentGetResponse;
import socialMediaApp.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	private final CommentService commentService;

	public CommentsController(CommentService commentService) {
		this.commentService = commentService;
	}

	// http://localhost:8080/api/comments/getall
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getall")
	public ResponseEntity<List<CommentGetResponse>> getAll() {
		return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
	}

	// http://localhost:8080/api/comments/getallbypost/{postId}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getallbypost/{postId}")
	public ResponseEntity<List<CommentGetResponse>> getAllByPost(@PathVariable int postId) {
		return new ResponseEntity<>(commentService.getAllByPost(postId), HttpStatus.OK);
	}

	// http://localhost:8080/api/comments/getallbyuser/{userId}
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/getallbyuser/{userId}")
	public ResponseEntity<List<CommentGetResponse>> getAllByUser(@PathVariable int userId) {
		return new ResponseEntity<>(commentService.getAllByUser(userId), HttpStatus.OK);
	}

	// http://localhost:8080/api/comments/add........userId...postId and description
//    {
//    	"userId":1,
//    	"postId":2,
//    	"description":"Nice"
//    	}
	@SecurityRequirement(name = "Authorization")
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest) throws CommentException {
		commentService.add(commentAddRequest);
		return new ResponseEntity<>("Added", HttpStatus.CREATED);
	}

	// http://localhost:8080/api/comments/delete?id=
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam int id) throws CommentException {
		commentService.delete(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
