package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда вывода информации о коллекции
 */
public class InfoCommand extends AbstractCommand {
    public InfoCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "info - информация о коллекции";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Тип коллекции: " + collectionManager.getCollectionType());
        System.out.println("Дата инициализации: " + collectionManager.getInitDate());
        System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
    }
}