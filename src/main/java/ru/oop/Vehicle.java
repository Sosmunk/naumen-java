package ru.oop;

/**
 * Транспорт
 */
public interface Vehicle extends Positioned {
    /**
     * Довозит человека до заданного места, если это возможно. <br>
     * В противном случае, везёт человека до ближайшей к destination точке.
     * @param person человек
     * @param destination место назначения
     */
    void movePerson(Person person, Position destination);
}
