package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Ticket;

/**
 * Команда удаления элементов меньше заданного
 */
public class RemoveLowerCommand extends AbstractCommand {
    public RemoveLowerCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "remove_lower - удалить меньшие элементы";
    }

    @Override
    public void execute(String[] args) {
        int newId = collectionManager.getTickets().stream()
                .mapToInt(Ticket::getId)
                .max()
                .orElse(0) + 1;

        Ticket ticket = inputManager.readTicket(newId);
        int beforeSize = collectionManager.getCollectionSize();
        collectionManager.removeLower(ticket);
        int removed = beforeSize - collectionManager.getCollectionSize();
        System.out.println("Удалено элементов: " + removed);
    }
}