package socialMediaApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.UserException;
import socialMediaApp.responses.userImage.UserImageResponse;
import socialMediaApp.services.UserImageService;

import java.io.IOException;

@RestController
@RequestMapping("/api/userimages")
public class UserImagesController {
	private final UserImageService userImageService;

	public UserImagesController(UserImageService userImageService) {
		this.userImageService = userImageService;
	}

	// http://localhost:8080/api/userimages/upload
	@SecurityRequirement(name = "Authorization")
	@PostMapping("/upload")
	public ResponseEntity<UserImageResponse> upload(@RequestParam("image") MultipartFile file, @RequestParam int userId)
			throws IOException, UserException {
		UserImageResponse response = userImageService.upload(file, userId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// http://localhost:8080/api/userimages/download/1
	@SecurityRequirement(name = "Authorization")
	@GetMapping("/download/{userId}")
	public ResponseEntity<byte[]> download(@PathVariable int userId) {
		byte[] image = userImageService.download(userId);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
	}

	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws UserException {
		userImageService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
