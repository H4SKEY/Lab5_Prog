package org.example.commands; /**
 * Команда удаления по типу
 */
import org.example.util.CollectionManager;
import org.example.util.InputManager;
import org.example.data.TicketType;

import java.util.Arrays;

public class RemoveAnyByTypeCommand extends AbstractCommand {
    public RemoveAnyByTypeCommand(CollectionManager collectionManager, InputManager inputManager) {
        super(collectionManager, inputManager);
    }

    public String description() {
        return "remove_any_by_type type - удалить по типу";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Не указан тип билета");
            return;
        }

        try {
            TicketType type = TicketType.valueOf(args[0].toUpperCase());
            collectionManager.removeAnyByType(type);
            System.out.println("Элемент типа " + type + " удален");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный тип билета. Допустимые значения: " +
                    Arrays.toString(TicketType.values()));
        }
    }
}