package example.bot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BotLogicTest {

    private MockBot bot;
    private BotLogic botLogic;
    private User user;

    @Before
    public void setUp() {
        bot = new MockBot();
        botLogic = new BotLogic(bot);
        user = new User(123L);
    }

    /**
     * Тест, который проверяет, будет ли бот отправлять сообщение об ошибке при неправильной команде
     */
    @Test
    public void CommandNotFoundTest() {

        botLogic.processCommand(user, "");

        assertEquals("Такой команды пока не существует," +
                " или Вы допустили ошибку в написании." +
                " Воспользуйтесь командой /help, чтобы прочитать инструкцию.", bot.getLastMessage());
    }

    /**
     * Тест на прохождение опроса
     */
    @Test
    public void CorrectQuizTest() {
        botLogic.processCommand(user, "/test");
        botLogic.processCommand(user, "100");

        assertEquals("Правильный ответ!", bot.getMessages().get(1));
        assertEquals("Сколько будет 2 + 2 * 2", bot.getLastMessage());
        botLogic.processCommand(user, "0");
        assertEquals("Вы ошиблись, верный ответ: 6", bot.getMessages().get(3));
        assertEquals("Тест завершен", bot.getLastMessage());
    }


    /**
     * Тест, который проверяет отсутствие вопросов в /repeat при правильном ответе на тест
     */
    @Test
    public void RepeatNoQuestionsTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "100");
        botLogic.processCommand(user, "6");

        botLogic.processCommand(user, "/repeat");
        assertEquals("Нет вопросов для повторения", bot.getLastMessage());
    }

    /**
     * Тест который проверяет верно ли /repeat возвращает вопрос, на который ответили неправильно
     */
    @Test
    public void CorrectRepeatCommandWrongAnswerTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "100");
        botLogic.processCommand(user, "3");
        botLogic.processCommand(user, "/repeat");
        assertEquals("Сколько будет 2 + 2 * 2", bot.getLastMessage());
        botLogic.processCommand(user, "6");
        assertEquals("Правильный ответ!", bot.getMessages().get(bot.getMessages().size() - 2));
        assertEquals("Тест завершен", bot.getLastMessage());
    }


    /**
     * Тест, проверяющий, верно ли работает логика /notify
     */
    @Test
    public void NotificationTest() throws InterruptedException {
        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания", bot.getLastMessage());
        botLogic.processCommand(user, "123");
        assertEquals("Через сколько секунд напомнить?", bot.getLastMessage());
        botLogic.processCommand(user, "1");

        Thread.sleep(990);
        assertEquals("Напоминание установлено", bot.getLastMessage());
        Thread.sleep(20);
        assertEquals("Сработало напоминание: '123'", bot.getLastMessage());
    }


}