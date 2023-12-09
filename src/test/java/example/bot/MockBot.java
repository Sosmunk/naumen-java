package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 * Моковая реализация бота
 */
public class MockBot implements Bot{

    /**
     * Сообщения отправленные пользователю
     */
    private final List<String> messages = new ArrayList<>();
    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    /**
     * Получить отправленные сообщения
     * @return сообщения
     */
    public List<String> getMessages() {
        return messages;
    }
}
