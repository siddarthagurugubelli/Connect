package socialMediaApp.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import socialMediaApp.models.ChatMessage;
import socialMediaApp.repositories.UserRepository;
import socialMediaApp.requests.ChatMessageRequest;
import socialMediaApp.responses.chatMessage.ChatMessageResponse;

@Mapper(componentModel = "spring")
public abstract class ChatMessageMapper {
	@Autowired 
	private UserRepository userRepository;

	public abstract ChatMessage chatMessageRequestToChatMessage(ChatMessageRequest request);

	public abstract ChatMessageResponse chatMessageToChatMessageResponse(ChatMessage chatMessage);

	public abstract List<ChatMessageResponse> chatMessagesToChatMessageResponses(List<ChatMessage> chatMessages);

}
