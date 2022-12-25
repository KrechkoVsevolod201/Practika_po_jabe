package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.PreparedStatement;

public class BookSearch implements CommandExecutor {

    private static final String SQL_SELECT = "Select* from books where books.id = ?";

    @Override
    public int execute(String text) {
        return searchBook(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.SELECT_BOOKS;
    }

    @SneakyThrows
    public static int searchBook(String text) {
        String[] words = text.split(" ");
        int set = 0;
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_SELECT);
        stat.setInt(1, Integer.parseInt(words[1]));
        System.out.println(words[1]);
        set = stat.executeUpdate();
        return set;
    }
}