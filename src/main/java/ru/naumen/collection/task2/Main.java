package ru.naumen.collection.task2;

public class Main {

    public static void main(String[] args) {
        Task2 task2 = new Task2();

        for (Ticket ticket : task2.getTickets()) {
            System.out.println(ticket.getClient() + " " + task2.getAdditionalServicesByTicket(ticket));
        }

    }
}
