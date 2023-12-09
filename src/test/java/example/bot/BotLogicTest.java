package example.bot;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
                " Воспользуйтесь командой /help, чтобы прочитать инструкцию.", bot.getMessages().get(0));
    }

    /**
     * Проверка работы команды /test
     */
    @Test
    public void CorrectTestCommand() {
        botLogic.processCommand(user, "/test");
        assertEquals(State.TEST, user.getState());
        assertEquals("Вычислите степень: 10^2", bot.getMessages().get(0));
    }

    /**
     * Тест на определение верного ответа
     */
    @Test
    public void CorrectAnswerTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "100");

        assertEquals("Правильный ответ!", bot.getMessages().get(1));
    }

    /**
     * Тест на отправку пользователю сообщения о неправильном ответе
     */
    @Test
    public void IncorrectAnswerTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "1");

        assertEquals("Вы ошиблись, верный ответ: 100", bot.getMessages().get(1));
    }

    /**
     * Проверка изменения стадии опроса
     */
    @Test
    public void ChangeQuestionIndexTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "100");

        assertEquals(1, user.getCurrentQuestionIndex());
    }

    /**
     * Проверка, что тест для пользователя корректно завершается
     */
    @Test
    public void CompleteQuizTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "100");
        botLogic.processCommand(user, "6");
        assertEquals("Тест завершен", bot.getMessages().get(bot.getMessages().size() - 1));
        assertEquals(State.INIT, user.getState());
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
        assertEquals("Нет вопросов для повторения", bot.getMessages().get(bot.getMessages().size() - 1));
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
        assertEquals(State.REPEAT, user.getState());
        assertEquals("Сколько будет 2 + 2 * 2", bot.getMessages().get(bot.getMessages().size() - 1));
        botLogic.processCommand(user, "6");
        assertEquals(State.INIT, user.getState());
    }

    /**
     * Проверяет, возвращает ли /repeat все неправильно отвеченные вопросы
     */
    @Test
    public void RepeatAllTest() {
        botLogic.processCommand(user, "/test");

        botLogic.processCommand(user, "10");
        botLogic.processCommand(user, "3");
        botLogic.processCommand(user, "/repeat");
        List<String> botMessages = bot.getMessages();
        assertEquals("Вычислите степень: 10^2", botMessages.get(botMessages.size() - 1));
        botLogic.processCommand(user, "100");
        assertEquals("Сколько будет 2 + 2 * 2", botMessages.get(botMessages.size() - 1));
        botLogic.processCommand(user, "333");
        assertEquals(State.INIT, user.getState());
    }

    /**
     * Тест, проверяющий, верно ли работает логика /notify
     */
    @Test
    public void NotificationTest() throws InterruptedException {
        List<String> botMessages = bot.getMessages();

        botLogic.processCommand(user, "/notify");
        assertEquals("Введите текст напоминания", bot.getMessages().get(0));
        botLogic.processCommand(user, "123");
        assertEquals("Через сколько секунд напомнить?", botMessages.get(botMessages.size() - 1));
        botLogic.processCommand(user, "1");
        assertEquals("Напоминание установлено", botMessages.get(botMessages.size() - 1));

        Thread.sleep(500);
        assertEquals("Напоминание установлено", botMessages.get(botMessages.size() - 1));
        Thread.sleep(600);
        assertEquals("Сработало напоминание: '123'", botMessages.get(botMessages.size() - 1));
    }


}