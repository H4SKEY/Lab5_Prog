package org.example;

import org.example.commands.*;
import org.example.util.CollectionManager;
import org.example.util.InputManager;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Главный класс приложения для управления коллекцией билетов
 */
public class Main {
    private static final String ENV_VAR_NAME = "LAB5_FILE";
    public static void main(String[] args) {
        // Получаем имя файла из переменной окружения
        String fileName = System.getenv(ENV_VAR_NAME);

        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Ошибка: Необходимо установить переменную окружения " + ENV_VAR_NAME);
            System.err.println("Пример для Linux/Mac: export LAB5_FILE=data.json");
            System.err.println("Пример для Windows: set LAB5_FILE=data.json");
            System.exit(1);
        }

        // Инициализация менеджеров
        CollectionManager collectionManager = new CollectionManager(fileName);
        Scanner scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager(scanner);

        // Регистрация всех доступных команд
        HashMap<String, AbstractCommand> commands = registerCommands(collectionManager, inputManager);

        System.out.println("=== Программа управления коллекцией билетов ===");
        System.out.println("Тип коллекции: " + collectionManager.getCollectionType());
        System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
        System.out.println("Для списка команд введите 'help'");

        // Основной цикл обработки команд
        runCommandLoop(commands, scanner);
    }

    /**
     * Регистрирует все доступные команды
     */
    private static HashMap<String, AbstractCommand> registerCommands(CollectionManager collectionManager,
                                                                     InputManager inputManager) {
        HashMap<String, AbstractCommand> commands = new HashMap<>();

        commands.put("help", new HelpCommand(collectionManager, inputManager));
        commands.put("info", new InfoCommand(collectionManager, inputManager));
        commands.put("show", new ShowCommand(collectionManager, inputManager));
        commands.put("add", new AddCommand(collectionManager, inputManager));
        commands.put("update", new UpdateCommand(collectionManager, inputManager));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, inputManager));
        commands.put("clear", new ClearCommand(collectionManager, inputManager));
        commands.put("save", new SaveCommand(collectionManager, inputManager));
        commands.put("execute_script", new SaveCommand(collectionManager, inputManager));
        commands.put("exit", new SaveCommand(collectionManager, inputManager));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, inputManager));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, inputManager));
        commands.put("reorder", new ReorderCommand(collectionManager, inputManager));
        commands.put("remove_any_by_type", new RemoveAnyByTypeCommand(collectionManager, inputManager));
        commands.put("count_by_person", new CountByPersonCommand(collectionManager, inputManager));
        commands.put("print_field_ascending_person", new PrintFieldAscendingPersonCommand(collectionManager, inputManager));

        return commands;
    }

    /**
     * Запускает основной цикл обработки команд
     */
    private static void runCommandLoop(HashMap<String, AbstractCommand> commands, Scanner scanner) {
        while (true) {
            try {
                System.out.print("> ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) continue;

                String[] parts = input.split(" ", 2);
                String commandName = parts[0].toLowerCase();
                String[] commandArgs = parts.length > 1 ? parts[1].split(" ") : new String[0];

                if (commandName.equals("exit")) {
                    System.out.println("Завершение программы...");
                    break;
                }

                if (commandName.equals("execute_script")) {
                    if (commandArgs.length > 0) {
                        executeScript(commandArgs[0], commands);
                    } else {
                        System.out.println("Не указано имя файла скрипта");
                    }
                    continue;
                }

                AbstractCommand command = commands.get(commandName);
                if (command != null) {
                    command.execute(commandArgs);
                } else {
                    System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Выполняет команды из скрипта
     */
    private static void executeScript(String filename, HashMap<String, AbstractCommand> commands) {
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