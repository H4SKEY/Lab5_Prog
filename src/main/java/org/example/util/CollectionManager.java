package org.example.util;

import org.example.data.Person;
import org.example.data.Ticket;
import org.example.data.TicketType;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Менеджер коллекции билетов с полной реализацией функционала
 */
public class CollectionManager {
    private boolean reverse = false;
    private List<Ticket> tickets;
    private final LocalDateTime initDate;
    private final String fileName;

    public String getFileName() {
        return fileName;
    }

    public CollectionManager(String fileName) {
        this.fileName = fileName;
        this.initDate = LocalDateTime.now();
        this.tickets = new ArrayList<>();
    }

//    Геттеры и сеттеры для reverse
    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    // Основные методы управления коллекцией

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void updateTicket(int id, Ticket newTicket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == id) {
                tickets.set(i, newTicket);
                return;
            }
        }
        throw new NoSuchElementException("Билет с ID " + id + " не найден");
    }

    public void removeTicket(int id) {
        if (!tickets.removeIf(t -> t.getId() == id)) {
            throw new NoSuchElementException("Билет с ID " + id + " не найден");
        }
    }

    public void sort() {
        Collections.sort(tickets);
        setReverse(false);
    }

    public void clear() {
        tickets.clear();
    }

    public void reorder() {
        Collections.reverse(tickets);
        setReverse(!isReverse());
    }

    public Ticket getMinTicket() {
        return tickets.stream().min(Ticket::compareTo).orElse(null);
    }

    public void removeLower(Ticket ticket) {
        tickets.removeIf(t -> t.compareTo(ticket) < 0);
    }

    public void removeAnyByType(TicketType type) {
        tickets.removeIf(t -> t.getType() == type);
    }

    public long countByPerson(Person person) {
        return tickets.stream()
                .filter(t -> t.getPerson().equals(person))
                .count();
    }

    public List<Person> getPersonsAscending() {
        return tickets.stream()
                .map(Ticket::getPerson)
                .sorted(Comparator.comparing(Person::getPassportID,
                        Comparator.nullsFirst(String::compareTo)))
                .collect(Collectors.toList());
    }

    // Геттеры для информации о коллекции

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getInitDate() {
        return initDate;
    }

    public String getCollectionType() {
        return "ArrayList<Ticket>";
    }

    public int getCollectionSize() {
        return tickets.size();
    }

    /**
     * Генерирует новый уникальный ID для билета
     */
    public int generateNewId() {
        if (tickets.isEmpty()) {
            return 1;
        }
        return tickets.stream().mapToInt(Ticket::getId).max().getAsInt() + 1;
    }
}