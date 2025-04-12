package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда удаления элемента по ID
 */
public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Не указан ID элемента");
            return;
        }

        try {
            int id = Integer.parseInt(args[0]);
            collectionManager.removeTicket(id);
            System.out.println("Элемент с ID " + id + " удален");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}