package socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import socialMediaApp.exceptions.UserException;
import socialMediaApp.models.Follow;
import socialMediaApp.models.User;
import socialMediaApp.responses.user.UserFollowingResponse;
import socialMediaApp.responses.user.UserResponse;
import socialMediaApp.services.UserService;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Test
	void getByIdTest() throws UserException {
		User user = userService.getById(1);
		assertEquals("Nuzhat@gmail.com",user.getEmail());
	}
	
	@Test
	void getUserFollowingTest() {
		List<UserFollowingResponse> following = userService.getUserFollowing(7);
				assertEquals(true,following.isEmpty());
		}
	
	@Test
	void isFollowingTrueTest() {
		boolean follow = userService.isFollowing(1, 2);
		assertTrue(follow);
	}
	
	@Test
	void isFollowingFalseTest() {
		boolean follow = userService.isFollowing(1, 9);
		assertFalse(follow);
	}


}
