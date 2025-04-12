package org.example.commands;

import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.Person;

/**
 * Команда подсчета по человеку
 */
public class CountByPersonCommand extends AbstractCommand {
    public CountByPersonCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    @Override
    public void execute(String[] args) {
        Person person = inputManager.readPerson();
        long count = collectionManager.countByPerson(person);
        System.out.println("Найдено элементов: " + count);
    }
}