package socialMediaApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import socialMediaApp.models.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

	void deleteById(int id);
}
