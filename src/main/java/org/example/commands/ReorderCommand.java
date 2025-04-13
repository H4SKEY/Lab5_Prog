package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда реверса коллекции
 */
public class ReorderCommand extends AbstractCommand {
    public ReorderCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "reorder - отсортировать в обратном порядке";
    }

    @Override
    public void execute(String[] args) {
        collectionManager.reorder();
        System.out.println("Коллекция отсортирована в обратном порядке");
    }
}