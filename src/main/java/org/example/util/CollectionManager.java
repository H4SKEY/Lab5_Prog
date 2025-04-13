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
    private List<Ticket> tickets;
    private final LocalDateTime initDate;
    private final String fileName;

    public CollectionManager(String fileName) {
        this.fileName = fileName;
        this.initDate = LocalDateTime.now();
        this.tickets = new ArrayList<>();
        loadCollection();
    }

    /**
     * Загружает коллекцию из файла
     */
    private void loadCollection() {
        try {
            List<Ticket> loadedTickets = JsonSerializer.loadFromFile(fileName);
            if (loadedTickets != null) {
                // Проверка на уникальность ID
                Set<Integer> ids = new HashSet<>();
                List<Ticket> validTickets = new ArrayList<>();

                for (Ticket ticket : loadedTickets) {
                    if (ids.contains(ticket.getId())) {
                        System.out.println("Ошибка: Найден дубликат ID " + ticket.getId());
                        continue;
                    }
                    if (ticket.getId() <= 0) {
                        System.out.println("Ошибка: Неверный ID " + ticket.getId());
                        continue;
                    }
                    ids.add(ticket.getId());
                    validTickets.add(ticket);
                }

                this.tickets = validTickets;
                System.out.println("Загружено " + tickets.size() + " элементов из файла " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Ошибка загрузки файла: " + e.getMessage());
            System.out.println("Будет создана пустая коллекция");
            this.tickets = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке коллекции: " + e.getMessage());
            this.tickets = new ArrayList<>();
        }
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        try {
            JsonSerializer.saveToFile(tickets, fileName);
            System.out.println("Коллекция успешно сохранена в файл " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения коллекции: " + e.getMessage());
        }
    }

    // Основные методы управления коллекцией

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        Collections.sort(tickets);
    }

    public void updateTicket(int id, Ticket newTicket) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId() == id) {
                tickets.set(i, newTicket);
                Collections.sort(tickets);
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

    public void clear() {
        tickets.clear();
    }

    public void reorder() {
        Collections.reverse(tickets);
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