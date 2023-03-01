package socialMediaApp.services;

import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.FollowException;
import socialMediaApp.mappers.FollowMapper;
import socialMediaApp.models.Follow;
import socialMediaApp.repositories.FollowRepository;
import socialMediaApp.requests.FollowRequest;


@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final UserService userService;

    public FollowService(FollowRepository followRepository, FollowMapper followMapper, UserService userService) {
        this.followRepository = followRepository;
        this.followMapper = followMapper;
        this.userService = userService;
    }

    public void add(FollowRequest followAddRequest) throws FollowException {
    	try {
        if (userService.isFollowing(followAddRequest.getUserId(), followAddRequest.getFollowingId())){
            return;
        }
        followRepository.save(followMapper.addRequestToFollow(followAddRequest));
    	} catch(Exception e) {
    		throw new FollowException("Unable to add");
    	}
    }

    public  void delete(FollowRequest followRequest) throws FollowException{
    	try {
      Follow follow = followRepository.findByUser_IdAndFollowing_Id(followRequest.getUserId(), followRequest.getFollowingId()).orElse(null);
        followRepository.delete(follow);
    	}catch(Exception e) {
    		throw new FollowException("Enter correct ID");
    	}
    }


}
