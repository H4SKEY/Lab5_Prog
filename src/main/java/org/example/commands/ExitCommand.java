package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

public class ExitCommand extends AbstractCommand {
    public ExitCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "execute_script file_name - выполнить скрипт";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Завершение программы...");
        System.exit(0);
    }
}
