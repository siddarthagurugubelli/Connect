package socialMediaApp.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
	@NotBlank
	private String message;
	private int senderId;
	private String email;

}
