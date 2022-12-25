package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.PreparedStatement;

public class TitlesRemove implements CommandExecutor {

    private static final String SQL_DELETE_TITLES = "delete from titles where book_id = ?";
    private static final String SQL_DELETE_BOOKS = "delete from books where book_id = ?";


    @Override
    public int execute(String text) {
        return removeTitle(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_TITLES;
    }

    @SneakyThrows
    public static int removeTitle(String text) {
        String[] words = text.split(" ");
        int setTitles, setBooks  = 0;
        PreparedStatement statTitles = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE_TITLES);
        PreparedStatement statBooks = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE_BOOKS);
        statBooks.setInt(1, Integer.parseInt(words[1]));
        setBooks = statBooks.executeUpdate();
        statTitles.setInt(1, Integer.parseInt(words[1]));
        setTitles = statTitles.executeUpdate();
        return setTitles + setBooks;
    }
}