package org.example.util;

import java.util.HashMap;
import java.util.Map;
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
        commands.put("help", new HelpCommand(this));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, inputManager));
        commands.put("update", new UpdateCommand(collectionManager, inputManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, inputManager));
        commands.put("clear", new ClearCommand(collectionManager));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager, inputManager));
        commands.put("exit", new ExitCommand());
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, inputManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, inputManager));
        commands.put("reorder", new ReorderCommand(collectionManager));
        commands.put("remove_any_by_type", new RemoveAnyByTypeCommand(collectionManager, inputManager));
        commands.put("count_by_person", new CountByPersonCommand(collectionManager, inputManager));
        commands.put("print_field_ascending_person", new PrintFieldAscendingPersonCommand(collectionManager));
    }

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }
}
