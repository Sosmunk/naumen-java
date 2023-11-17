package example.bot;

import java.util.ArrayList;
import java.util.List;

public class MockBot implements Bot{

    private final List<String> messages = new ArrayList<>();
    @Override
    public void sendMessage(Long chatId, String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
