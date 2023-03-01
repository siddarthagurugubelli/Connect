package socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import socialMediaApp.responses.comment.CommentGetResponse;
import socialMediaApp.services.CommentService;

@SpringBootTest
public class CommentServiceTest {

	@Autowired
	CommentService commentService;
	
	@Test
	void getAllTest() {
		List<CommentGetResponse> comments = commentService.getAll();
		assertEquals(8, comments.size());
	}
	
	@Test
	void getByIdUserTest() {
		CommentGetResponse comments = commentService.getById(1);
		assertEquals(1,comments.getUserId());
	}
	
	@Test
	void getByIdDescriptionTest() {
		CommentGetResponse comments = commentService.getById(1);
		assertEquals("Nice",comments.getDescription());
	}
	
	@Test
	void getAllByPostTest() {
		List<CommentGetResponse> comments = commentService.getAllByPost(5);
		assertEquals(0, comments.size());
	}
	
	@Test
	void getAllByUserTest() {
		List<CommentGetResponse> comments = commentService.getAllByUser(1);
		assertEquals(3, comments.size());

	}
}
