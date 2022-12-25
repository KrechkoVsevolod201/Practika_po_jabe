package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.PreparedStatement;

public class TitlesRemove implements CommandExecutor {

    private static final String SQL_DELETE = "delete from titles where book_id = ?";

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
        int set = 0;
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE);
        stat.setInt(1, Integer.parseInt(words[1]));
        set = stat.executeUpdate();
        return set;
    }
}