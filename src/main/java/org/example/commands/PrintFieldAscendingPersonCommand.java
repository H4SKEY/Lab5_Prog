package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Person;

import java.util.List;

public class PrintFieldAscendingPersonCommand extends AbstractCommand {

    public PrintFieldAscendingPersonCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager); // Передаем параметры в родительский класс
    }

    @Override
    public void execute(String[] args) {
        List<Person> persons = collectionManager.getPersonsAscending();

        if (persons.isEmpty()) {
            inputManager.showMessage("Коллекция пуста");
            return;
        }

        inputManager.showMessage("Значения поля person в порядке возрастания:");
        persons.forEach(person -> {
            System.out.println(person); // Или кастомный вывод через inputManager
        });
    }
}