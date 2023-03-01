package socialMediaApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import socialMediaApp.models.ChatMessage;
import socialMediaApp.responses.chatMessage.ChatMessageResponse;
import socialMediaApp.services.ChatMessageService;

@SpringBootTest
public class ChatMessageTest {
	
	@Autowired
	ChatMessageService chatMessageService;
	
	@Test
	void getAllTest() {
		List<ChatMessageResponse> chatMessage = chatMessageService.getAll();
		assertEquals(11,chatMessage.size());
	}

}
