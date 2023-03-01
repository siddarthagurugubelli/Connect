package socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import socialMediaApp.models.Post;
import socialMediaApp.responses.post.PostGetResponse;
import socialMediaApp.services.PostService;

@SpringBootTest
public class PostTest {
	@Autowired
	PostService postService;
	
	@Test
	void getAllTest() {
		List<PostGetResponse> post = postService.getAll();
		assertEquals(7, post.size());
	}
	
	@Test
	void getByIdTest() {
		Post post = postService.getById(1);
		assertEquals("My first post",post.getDescription());
	}
}
