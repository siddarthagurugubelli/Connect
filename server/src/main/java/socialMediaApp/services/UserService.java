package socialMediaApp.services;

import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.UserException;
import socialMediaApp.mappers.UserMapper;
import socialMediaApp.models.Follow;
import socialMediaApp.models.User;
import socialMediaApp.repositories.FollowRepository;
import socialMediaApp.repositories.UserRepository;
import socialMediaApp.requests.UserAddRequest;
import socialMediaApp.responses.user.UserFollowingResponse;
import socialMediaApp.responses.user.UserResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
   private final UserMapper userMapper;
   private final UserRepository userRepository;
   private final FollowRepository followRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository, FollowRepository followRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }

    public List<UserResponse> getAll(){

        return userMapper.usersToResponses(userRepository.findAll());
    }
    
    public UserResponse getResponseById(int id) throws UserException{
    	try {
        User user = userRepository.findById(id).get();
        return userMapper.userToResponse(user);
    	}catch(Exception e) {
    		throw new UserException("ID not present, enter correct ID");
    	}
    }

    public UserResponse getByEmail(String email){
        User user = userRepository.findByEmail(email);
        return userMapper.userToResponse(user);
    }

    public List<UserFollowingResponse> getUserFollowing(int userId){
        return userMapper.followsToFollowingResponses(followRepository.findAllByUser_Id(userId));
    }

    public boolean isFollowing(int userId,int followingId){
       Optional<Follow> follow = followRepository.findByUser_IdAndFollowing_Id(userId,followingId);
       return follow.isPresent();
    }

    public User getById(int id) throws UserException{
    	try {
        return userRepository.findById(id).get();
    	}catch(Exception e) {
    		throw new UserException("ID not present, enter correct Id");
    	}
    }
    
    public void add(UserAddRequest userAddRequest) throws UserException{
    	try {
        User user = userMapper.requestToUser(userAddRequest);
        userRepository.save(user);
    	}catch(Exception e) {
    		throw new UserException("User not able to add");
    	}
    }

    public void delete(int id) throws UserException{
    	try {
        userRepository.deleteById(id);
    	}catch(Exception e) {
    		throw new UserException("ID does not exists");
    	}
    }
}
