package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Ticket;

/**
 * Команда добавления элемента
 */
public class AddCommand extends AbstractCommand {
    public AddCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "add - добавить новый элемент";
    }

    @Override
    public void execute(String[] args) {
        // Генерация ID - находим максимальный существующий и добавляем 1
        int newId = collectionManager.generateNewId();

        Ticket ticket = inputManager.readTicket(newId);
        collectionManager.addTicket(ticket);
        System.out.println("Билет добавлен с ID: " + newId);
    }
}