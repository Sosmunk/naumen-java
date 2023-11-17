package example.bot;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotLogicTest {
    /**
     *
     */
    @Test
    public void CorrectTestCommand() {
        MockBot bot = new MockBot();
        BotLogic botLogic = new BotLogic(bot);
        User user = new User(123L);

        botLogic.processCommand(user, "/test");
        assertEquals(State.TEST ,user.getState());
        //TODO:
    }

}