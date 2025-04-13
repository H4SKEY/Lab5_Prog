package org.example.util;

import java.util.HashMap;
import org.example.commands. *;


public class CommandManager {
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();
    private final CollectionManager collectionManager;
    private final InputManager inputManager;

    public CommandManager(CollectionManager collectionManager, InputManager inputManager) {
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        registerCommands();
    }

    private void registerCommands() {
        commands.put("help", new HelpCommand(collectionManager, inputManager, this));
        commands.put("info", new InfoCommand(collectionManager, inputManager));
        commands.put("show", new ShowCommand(collectionManager, inputManager));
        commands.put("add", new AddCommand(collectionManager, inputManager));
        commands.put("update", new UpdateCommand(collectionManager, inputManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, inputManager));
        commands.put("clear", new ClearCommand(collectionManager, inputManager));
        commands.put("save", new SaveCommand(collectionManager, inputManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager, inputManager, this));
        commands.put("exit", new ExitCommand(collectionManager, inputManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, inputManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, inputManager));
        commands.put("reorder", new ReorderCommand(collectionManager, inputManager));
        commands.put("remove_any_by_type", new RemoveAnyByTypeCommand(collectionManager, inputManager));
        commands.put("count_by_person", new CountByPersonCommand(collectionManager, inputManager));
        commands.put("print_field_ascending_person", new PrintFieldAscendingPersonCommand(collectionManager, inputManager));
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }
}
