package socialMediaApp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import socialMediaApp.exceptions.UserException;
import socialMediaApp.mappers.UserImageMapper;
import socialMediaApp.models.UserImage;
import socialMediaApp.repositories.UserImageRepository;
import socialMediaApp.responses.userImage.UserImageResponse;
import socialMediaApp.utils.ImageUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserImageService {

    private final UserImageRepository userImageRepository;
    private final UserService userService;
    private final UserImageMapper userImageMapper;

    public UserImageService(UserImageRepository userImageRepository, UserService userService, UserImageMapper userImageMapper) {
        this.userImageRepository = userImageRepository;
        this.userService = userService;
        this.userImageMapper = userImageMapper;
    }

    public UserImageResponse upload(MultipartFile file,int userId) throws IOException, UserException {
        UserImage userImage = new UserImage();
        UserImage userExistingData = userImageRepository.findByUser_Id(userId).orElse(null);
        if(userExistingData != null) {
        userExistingData.setData(ImageUtil.compressImage(file.getBytes()));
        userExistingData.setName(file.getOriginalFilename());
        userExistingData.setType(file.getContentType());
        userExistingData.setUser(userService.getById(userId));
        userImageRepository.save(userExistingData);
        return userImageMapper.userImageToResponse(userExistingData);
        }
        else {
        userImage.setData(ImageUtil.compressImage(file.getBytes()));
        userImage.setName(file.getOriginalFilename());
        userImage.setType(file.getContentType());
        userImage.setUser(userService.getById(userId));
        userImageRepository.save(userImage);
        return userImageMapper.userImageToResponse(userImage);
        }
        
    }

    public byte[] download(int id){
        Optional<UserImage> userImage = userImageRepository.findByUser_Id(id);
        return ImageUtil.decompressImage(userImage.get().getData());
    }
    
    public void delete(int id) throws UserException{
    	try {
        userImageRepository.deleteById(id);
    	}catch(Exception e) {
    		throw new UserException("ID does not exists");
    	}
    }

}
