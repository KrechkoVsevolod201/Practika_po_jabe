package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.PreparedStatement;

public class BooksRemove implements CommandExecutor {

    private static final String SQL_DELETE = "delete from books where id = ?";

    @Override
    public int execute(String text) {
        return removeTextBook(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_BOOKS;
    }

    @SneakyThrows
    public static int removeTextBook(String text) {
        String[] words = text.split(" ");
        int set = 0;
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE);
        stat.setInt(1, Integer.parseInt(words[1]));
        set = stat.executeUpdate();
        return set;
    }
}