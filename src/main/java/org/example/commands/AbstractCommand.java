package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

public abstract class AbstractCommand implements Command {
    protected final CollectionManager collectionManager;
    protected final InputManager inputManager;

    public AbstractCommand(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
    }

    public abstract void execute(String[] args);
    public abstract String description();
}