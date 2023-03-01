package socialMediaApp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.PostException;
import socialMediaApp.mappers.PostMapper;
import socialMediaApp.models.Post;
import socialMediaApp.repositories.PostRepository;
import socialMediaApp.requests.PostAddRequest;
import socialMediaApp.responses.post.PostGetResponse;
import socialMediaApp.responses.user.UserFollowingResponse;

import java.util.*;

@Service
public class PostService {

	private final PostRepository postRepository;
	private final PostMapper postMapper;
	private final UserService userService;

	public PostService(PostRepository postRepository, PostMapper postMapper, UserService userService) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.userService = userService;
	}

	public List<PostGetResponse> getAll() {
		List<Post> posts = postRepository.findAll();
		return postMapper.postsToGetResponses(posts);
	}

	public PostGetResponse getResponseById(int id) throws PostException {
		try {
			Post post = postRepository.findById(id).orElse(null);
			return postMapper.postToGetResponse(post);
		} catch (Exception e) {
			throw new PostException("Post does not exists");
		}
	}

	public Post getById(int id) {
		return postRepository.findById(id).get();
	}

	public List<PostGetResponse> getAllByUser(int userId) throws PostException {
		try {
			List<Post> userPosts = postRepository.findAllByUser_IdOrderByIdDesc(userId);
			return postMapper.postsToGetResponses(userPosts);
		} catch (Exception e) {
			throw new PostException("Enter correct userId");
		}
	}

	public List<PostGetResponse> getByUserFollowing(int userId) {
		List<UserFollowingResponse> follows = userService.getUserFollowing(userId);
		List<Post> set = new ArrayList<>();

		for (UserFollowingResponse user : follows) {
			set.addAll(postRepository.findAllByUser_IdOrderByIdDesc(user.getUserId()));
		}

		set.sort(Comparator.comparing(Post::getId).reversed());

		return postMapper.postsToGetResponses(set);
	}

	public int add(PostAddRequest postAddRequest) throws PostException {
		try {
			Post post = postMapper.postAddRequestToPost(postAddRequest);
			postRepository.save(post);
			return post.getId();
		} catch (Exception e) {
			throw new PostException("Error in adding post");
		}
	}

	public ResponseEntity<Map<String, Boolean>> deletePost(int postId) throws PostException {
		Post posts;
		try {
			posts = postRepository.findById(postId)
					.orElseThrow(() -> new PostException("Post does not exist with id :" + postId));
			postRepository.delete(posts);
		
		} catch (PostException e) {
			System.out.println(e.getMessage());

		}
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
