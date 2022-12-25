package prog.utils;

import java.util.Map;
import java.util.Scanner;

public class CommandReader {

    static {
        printCommands();
    }

    public static void startReadCommand() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите вашу команду: ");
            String command = scanner.nextLine();
            if (readCommand(command) == 0) {
                break;
            }
        }
        scanner.close();
    }

    private static final Map<CommandType, CommandExecutor> COMMAND_EXECUTORS_GROUPED_BY_COMMAND = Map.of(
            CommandType.CREATE_BOOKS, new BooksCreate(),
            CommandType.DELETE_BOOKS, new BooksRemove(),
            CommandType.CREATE_TITLES, new TitlesCreate(),
            CommandType.DELETE_TITLES, new TitlesRemove()
    );

    private static int readCommand(String command) {
        CommandType commandType = getCommandType(command);
        if (COMMAND_EXECUTORS_GROUPED_BY_COMMAND.containsKey(commandType)) {
            var commandExecutor = COMMAND_EXECUTORS_GROUPED_BY_COMMAND.get(commandType);
            return commandExecutor.execute(command);
        }
        if (commandType == CommandType.EXIT) {
            return 0;
        }
        System.out.println("Неверная команда!");
        return 1;
    }

    private static CommandType getCommandType(String commandString) {
        if (commandString.contains("1.")) {
            return CommandType.CREATE_TITLES;
        }
        if (commandString.contains("2.")) {
            return CommandType.CREATE_BOOKS;
        }
        if (commandString.contains("3.")) {
            return CommandType.DELETE_TITLES;
        }
        if (commandString.contains("4.")) {
            return CommandType.DELETE_BOOKS;
        }
        if (commandString.contains("5.")) {
            return CommandType.WRITE_ALL;
        }
        if (commandString.contains("6.")) {
            return CommandType.EXIT;
        }
        return CommandType.UNDEFINED;
    }

    private static void printCommands() {
        System.out.println("Список доступных команд:\n" +
                "   1 - Создать студента: 1. <№ зачетной книги> <Имя> <Фамилия> \n" +
                "   2 - Создать учебник: 2. <№ учебника> <Название> <№ зачетной книжки студента> \n" +
                "   3 - Отчислить студента: 3. <№ зачетной книжки> (при этом удалятся все закрепленные за студентом учебники) \n" +
                "   4 - Удалить учебник: 4. <№ учебника> \n" +
                "   5 - Вывести всех студентов с учебниками на руках: 5. \n" +
                "   6 - Выйти: 6. \n");
    }
}