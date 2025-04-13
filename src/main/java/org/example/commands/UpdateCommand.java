package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Ticket;

/**
 * Команда обновления элемента по ID
 */
public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "update id - обновить элемент по ID";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Не указан ID элемента");
            return;
        }

        try {
            int id = Integer.parseInt(args[0]);
            Ticket existing = collectionManager.getTickets().stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (existing == null) {
                System.out.println("Элемент с ID " + id + " не найден");
                return;
            }

            Ticket updated = inputManager.readTicket(id);
            collectionManager.updateTicket(id, updated);
            System.out.println("Элемент с ID " + id + " обновлен");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}