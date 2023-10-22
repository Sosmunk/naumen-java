package ru.naumen.collection.task2;

import java.util.UUID;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    public Ticket(String client) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.getMostSignificantBits();
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public String getClient() {
        return client;
    }
}
