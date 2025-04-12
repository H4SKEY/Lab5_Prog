package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда сохранения коллекции
 */
public class SaveCommand extends AbstractCommand {
    public SaveCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    @Override
    public void execute(String[] args) {
        collectionManager.saveCollection();
    }
}