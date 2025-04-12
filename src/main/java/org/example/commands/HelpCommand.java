package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;

/**
 * Команда вывода справки
 */
public class HelpCommand extends AbstractCommand {
    public HelpCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Доступные команды:");
        System.out.println("help - вывести справку по командам");
        System.out.println("info - информация о коллекции");
        System.out.println("show - показать все элементы коллекции");
        System.out.println("add - добавить новый элемент");
        System.out.println("update id - обновить элемент по ID");
        System.out.println("remove_by_id id - удалить элемент по ID");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("execute_script file_name - выполнить скрипт");
        System.out.println("exit - выйти без сохранения");
        System.out.println("add_if_min - добавить если меньше минимального");
        System.out.println("remove_lower - удалить меньшие элементы");
        System.out.println("reorder - отсортировать в обратном порядке");
        System.out.println("remove_any_by_type type - удалить по типу");
        System.out.println("count_by_person - подсчитать по человеку");
        System.out.println("print_field_ascending_person - вывести людей по возрастанию");
    }
}