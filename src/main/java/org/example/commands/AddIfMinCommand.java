package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Ticket;

/**
 * Команда добавления если элемент минимальный
 */
public class AddIfMinCommand extends AbstractCommand {
    public AddIfMinCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "add_if_min - добавить если меньше минимального";
    }

    @Override
    public void execute(String[] args) {
        int newId = collectionManager.getTickets().stream()
                .mapToInt(Ticket::getId)
                .max()
                .orElse(0) + 1;

        Ticket newTicket = inputManager.readTicket(newId);
        Ticket minTicket = collectionManager.getMinTicket();

        if (minTicket == null || newTicket.compareTo(minTicket) < 0) {
            collectionManager.addTicket(newTicket);
            System.out.println("Элемент добавлен (ID: " + newId + ")");
        } else {
            System.out.println("Элемент не является минимальным, добавление отменено");
        }
    }
}
