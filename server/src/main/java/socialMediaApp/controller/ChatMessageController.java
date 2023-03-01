package socialMediaApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import socialMediaApp.exceptions.ChatMessageException;
import socialMediaApp.requests.ChatMessageRequest;
import socialMediaApp.responses.chatMessage.ChatMessageResponse;
import socialMediaApp.services.ChatMessageService;

@RestController
@RequestMapping("api/chatMessages")
public class ChatMessageController {
	private final ChatMessageService chatMessageService;

	public ChatMessageController(ChatMessageService chatMessageService) {
		this.chatMessageService = chatMessageService;
	}

	// http://localhost:8080/api/chatMessages
	@SecurityRequirement(name = "Authorization")
	@GetMapping("")
	public ResponseEntity<List<ChatMessageResponse>> getAll() {
		List<ChatMessageResponse> chatMessages = chatMessageService.getAll();
		return new ResponseEntity<>(chatMessages, HttpStatus.OK);
	}

	// http://localhost:8080/api/chatMessages
//	{
//		"senderId":1,
//		"email":"Nuzhat@gmail.com",
//		"message":"I am Nuzhat Sultana"
//		}
	@SecurityRequirement(name = "Authorization")
	@PostMapping("")
	public ResponseEntity<String> add(@RequestBody @Valid ChatMessageRequest chatMessageRequest)
			throws ChatMessageException {
		String message = chatMessageService.add(chatMessageRequest);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	// http://localhost:8080/api/chatMessages/3
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws ChatMessageException {
		chatMessageService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
