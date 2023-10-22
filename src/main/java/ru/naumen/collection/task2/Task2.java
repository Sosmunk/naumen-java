package ru.naumen.collection.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}<br>
 * <b><p>Алгоритмическая сложность O(1)</p></b>
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    // Коллекция для демонстрации работы, не является необходимой частью задачи
    private final List<Ticket> tickets = new ArrayList<>();

    // Используется для быстрого доступа за O(1) по Ticket.id
    private final Map<Long, Lunch> additionalServices = new HashMap<>();

    public Task2() {
        this.buyTicket("petya", Lunch.NO_PRODUCTS);
        this.buyTicket("vasya", Lunch.DRINKS);
        this.buyTicket("volodya", Lunch.FOOD_AND_DRINKS);
    }

    // Метод для демонстрации работы, не является необходимой частью задачи
    public void buyTicket(String client, Lunch lunch) {
        Ticket ticket = new Ticket(client);
        tickets.add(ticket);
        additionalServices.put(ticket.getId(), lunch);
    }

    // Алгоритмическая сложность O(1)
    public Lunch getAdditionalServicesByTicket(Ticket ticket) {
        return additionalServices.getOrDefault(ticket.getId(), null);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
