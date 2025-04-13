package org.example;

import org.example.commands.*;
import org.example.util.CollectionManager;
import org.example.util.CommandManager;
import org.example.util.FileManager;
import org.example.util.InputManager;

import java.util.HashMap;
import java.util.Scanner;

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
        CommandManager commandManager = new CommandManager(collectionManager, inputManager);
        FileManager fileManager = new FileManager(collectionManager);
        fileManager.loadCollection();


        // Регистрация всех доступных команд
        HashMap<String, AbstractCommand> commands = commandManager.getCommands();

        System.out.println("=== Программа управления коллекцией билетов ===");
        System.out.println("Тип коллекции: " + collectionManager.getCollectionType());
        System.out.println("Количество элементов: " + collectionManager.getCollectionSize());
        System.out.println("Для списка команд введите 'help'");

        // Основной цикл обработки команд
        runCommandLoop(commands, scanner);
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

                String[] parts = input.split("\\s+", 2);
                String commandName = parts[0].toLowerCase();
                String[] commandArgs = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

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
    }
}
