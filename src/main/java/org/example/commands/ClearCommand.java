package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда очистки коллекции
 */
public class ClearCommand extends AbstractCommand {
    public ClearCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "clear - очистить коллекцию";
    }

    @Override
    public void execute(String[] args) {
        collectionManager.clear();
        System.out.println("Коллекция очищена");
    }
}