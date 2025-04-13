package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.CommandManager;
import org.example.util.InputManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ExecuteScriptCommand extends AbstractCommand {
    private final CommandManager commandManager;

    public ExecuteScriptCommand(CollectionManager collectionManager, InputManager inputManager, CommandManager commandManager) {
        super(collectionManager, inputManager);
        this.commandManager = commandManager;
    }

    public String description() {
        return "execute_script file_name - выполнить скрипт";
    }

    @Override
    public void execute(String[] args) {
        String filename = args[0];
        HashMap<String, AbstractCommand> commands = commandManager.getCommands();

        try (Scanner scriptScanner = new Scanner(new File(filename))) {
            while (scriptScanner.hasNextLine()) {
                String line = scriptScanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                System.out.println("Выполняю: " + line);
                String[] parts = line.split(" ", 2);
                String commandName = parts[0].toLowerCase();
                String[] commandArgs = parts.length > 1 ? parts[1].split(" ") : new String[0];

                if (commandName.equals("execute_script")) {
                    System.out.println("Ошибка: Рекурсивный вызов скриптов запрещен");
                    continue;
                }

                AbstractCommand command = commands.get(commandName);
                if (command != null) {
                    command.execute(commandArgs);
                } else {
                    System.out.println("Неизвестная команда: " + commandName);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл скрипта не найден: " + filename);
        } catch (Exception e) {
            System.out.println("Ошибка выполнения скрипта: " + e.getMessage());
        }
    }
}
