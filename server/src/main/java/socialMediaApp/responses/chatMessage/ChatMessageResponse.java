package socialMediaApp.responses.chatMessage;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageResponse {
	private int id;
	private String message;
	private LocalDateTime createdAt;
	private int senderId;
	private String email;
}
