package socialMediaApp.services;

import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.CommentException;
import socialMediaApp.mappers.CommentMapper;
import socialMediaApp.models.Comment;
import socialMediaApp.repositories.CommentRepository;
import socialMediaApp.requests.CommentAddRequest;
import socialMediaApp.requests.CommentUpdateRequest;
import socialMediaApp.responses.comment.CommentGetResponse;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public void add(CommentAddRequest commentAddRequest) throws CommentException{
    	try {
        Comment comment = commentMapper.addRequestToComment(commentAddRequest);
        commentRepository.save(comment);
    	}catch(Exception e) {
    		throw new CommentException("Comment not added");
    	}
    }

    public List<CommentGetResponse> getAll(){
        List<Comment> comments = commentRepository.findAll();
        return commentMapper.commentsToResponses(comments);
    }

    public CommentGetResponse getById(int id){
        Comment comment = commentRepository.findById(id).orElse(null);
        return  commentMapper.commentToResponse(comment);
    }

    public List<CommentGetResponse> getAllByPost(int postId){
        List<Comment> comments = commentRepository.findAllByPost_Id(postId);
        return commentMapper.commentsToResponses(comments);
    }

    public List<CommentGetResponse> getAllByUser(int userId){
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        return commentMapper.commentsToResponses(comments);
    }
    
    public void update(int id, CommentUpdateRequest commentUpdateRequest){
        Comment commentToUpdate = commentRepository.findById(id).orElse(null);
        if (commentToUpdate!=null){
            commentToUpdate.setDescription(commentUpdateRequest.getDescription());
        }
    }

    public void delete(int id) throws CommentException{
    	try {
        commentRepository.deleteById(id);
    	}catch(Exception e) {
    		throw new CommentException("Enter valid ID");
    	}
    }
}
