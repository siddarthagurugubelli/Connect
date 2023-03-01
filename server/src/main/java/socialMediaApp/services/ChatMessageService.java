package socialMediaApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import socialMediaApp.exceptions.ChatMessageException;
import socialMediaApp.mappers.ChatMessageMapper;
import socialMediaApp.models.ChatMessage;
import socialMediaApp.models.User;
import socialMediaApp.repositories.ChatMessageRepository;
import socialMediaApp.repositories.UserRepository;
import socialMediaApp.requests.ChatMessageRequest;
import socialMediaApp.responses.chatMessage.ChatMessageResponse;

@Service
public class ChatMessageService {
	private final ChatMessageRepository chatMessageRepository;
	private final UserRepository userRepository;
	private final ChatMessageMapper chatMessageMapper;

	public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatMessageMapper chatMessageMapper,
			UserRepository userRepository) {
		this.chatMessageRepository = chatMessageRepository;
		this.chatMessageMapper = chatMessageMapper;
		this.userRepository = userRepository;
	}

	public List<ChatMessageResponse> getAll() {
		List<ChatMessage> chatMessages = chatMessageRepository.findAll();
		return chatMessageMapper.chatMessagesToChatMessageResponses(chatMessages);
	}

	public String add(ChatMessageRequest chatMessageAddRequest) throws ChatMessageException{
		try {
			ChatMessage chatMessage = chatMessageMapper.chatMessageRequestToChatMessage(chatMessageAddRequest);
			User user = userRepository.findById(chatMessage.getSenderId()).orElse(null);

			if (user.getEmail().equalsIgnoreCase(chatMessage.getEmail())) {
				chatMessageRepository.save(chatMessage);
				return chatMessage.getMessage();
			} else {
				throw new ChatMessageException("Invalid emailId");
			}
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public void delete(int id) throws ChatMessageException{
		try {
		chatMessageRepository.deleteById(id);
		}catch(Exception e) {
			throw new ChatMessageException("ID not found");
		}
	}

}
