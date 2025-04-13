package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Ticket;

import java.util.List;

public class ShowCommand extends AbstractCommand {

    public ShowCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "show - показать все элементы коллекции";
    }

    @Override
    public void execute(String[] args) {
        List<Ticket> tickets = collectionManager.getTickets();

        if (tickets.isEmpty()) {
            inputManager.showMessage("Коллекция пуста");
            return;
        }

        if (!collectionManager.isReverse()) {
            collectionManager.sort();
        }

        inputManager.showMessage("=== Элементы коллекции ===");
        tickets.forEach(ticket -> {
            inputManager.showMessage(formatTicket(ticket));
        });
        inputManager.showMessage("=== Всего элементов: " + tickets.size() + " ===");
    }

    private String formatTicket(Ticket ticket) {
        return String.format(
                "ID: %d | Название: %s | Цена: %d | Тип: %s",
                ticket.getId(),
                ticket.getName(),
                ticket.getPrice(),
                ticket.getType()
        );
    }
}