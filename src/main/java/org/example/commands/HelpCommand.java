package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.CommandManager;
import org.example.util.InputManager;

import java.util.HashMap;

/**
 * Команда вывода справки
 */
public class HelpCommand extends AbstractCommand {
    private final CommandManager commandManager;

    public HelpCommand(CollectionManager collectionManager, InputManager inputManager, CommandManager commandManager) {
        super(collectionManager, inputManager);
        this.commandManager = commandManager;
    }

    public String description() {
        return "help - вывести справку по командам";
    }

    @Override
    public void execute(String[] args) {
        HashMap<String, AbstractCommand> commands = commandManager.getCommands();

        for (AbstractCommand command: commands.values()) {
            System.out.println(command.description());
        }
    }
}