package socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import socialMediaApp.exceptions.LikeException;
import socialMediaApp.models.Like;
import socialMediaApp.responses.like.LikeResponse;
import socialMediaApp.services.LikeService;

@SpringBootTest
public class LikeTest {

	@Autowired
	LikeService likeservice;
	
	@Test
	void getAllByPostTest() throws LikeException{
		List<LikeResponse> likes = likeservice.getAllByPost(7);
		assertEquals(1,likes.size());
	}

 	@Test
 	void getAllByUserTest() throws LikeException{
 		List<LikeResponse> likes = likeservice.getAllByUser(1);
 		assertEquals(2,likes.size());
 	}

 	@Test
 	void isLikedTrueTest() throws LikeException{
 		boolean likes = likeservice.isLiked(1,3);
 		assertTrue(likes);
 	}
 	
 	@Test
 	void isLikedFlaseTest() throws LikeException{
 		boolean likes = likeservice.isLiked(1,7);
 		assertFalse(likes);
 	}
}