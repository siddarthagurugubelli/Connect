package socialMediaApp.services;

import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.LikeException;
import socialMediaApp.mappers.LikeMapper;
import socialMediaApp.models.Like;
import socialMediaApp.repositories.LikeRepository;
import socialMediaApp.requests.LikeRequest;
import socialMediaApp.responses.like.LikeResponse;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeService(LikeRepository likeRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    public List<LikeResponse> getAllByPost(int postId) throws LikeException{
    	try {
        List<Like> likes = likeRepository.findAllByPost_Id(postId);
        return likeMapper.likesToLikeResponses(likes);
    	}catch(Exception e) {
    		throw new LikeException("Enter correct postId");
    	}
    }

    public List<LikeResponse> getAllByUser(int userId) throws LikeException{
    	try {
        List<Like> likes = likeRepository.findAllByUser_Id(userId);
        return likeMapper.likesToLikeResponses(likes);
    	}catch(Exception e) {
    		throw new LikeException("Enter correct userId");
    	}
    }

    public boolean isLiked(int userId,int postId){
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(userId,postId);
        return like.isPresent();
    }

    public void add(LikeRequest likeRequest) throws LikeException{
    	try {
        if (isLiked(likeRequest.getUserId(), likeRequest.getPostId())){
            return;
        }
        Like like = likeMapper.requestToLike(likeRequest);
        likeRepository.save(like);
    	}catch(Exception e) {
    		throw new LikeException("Not able to like");
    	}
    }

    public void delete(LikeRequest likeRequest) throws LikeException{
    	try {
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(likeRequest.getUserId(),likeRequest.getPostId());
        likeRepository.delete(like.get());
    	}catch(Exception e) {
    		throw new LikeException("Enter correct ID");
    	}
    }

}
