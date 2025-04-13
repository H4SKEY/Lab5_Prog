package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.FileManager;
import org.example.util.InputManager;

/**
 * Команда сохранения коллекции
 */
public class SaveCommand extends AbstractCommand {
    public SaveCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "save - сохранить коллекцию в файл";
    }

    @Override
    public void execute(String[] args) {
        FileManager fileManager = new FileManager(collectionManager);
        fileManager.saveCollection();
    }
}