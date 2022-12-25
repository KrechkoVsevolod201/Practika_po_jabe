package prog.utils;

import lombok.SneakyThrows;
import prog.ApplicationDataSource;

import java.sql.Statement;

public class TitlesCreate implements CommandExecutor {
    @Override
    public int execute(String text) {
        return createTitles(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_TITLES;
    }

    @SneakyThrows
    public static int createTitles(String text) {
        String[] words = text.split(" ");
        int set = 0;
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        set = statement.executeUpdate("insert into titles(book_id, book_title) values(" + words[1]
                + ",'" + words[2] + "'" + ");");
        statement.close();
        return set;
    }
}