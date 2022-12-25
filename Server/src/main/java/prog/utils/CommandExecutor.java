package prog.utils;

import prog.utils.CommandType;

public interface CommandExecutor {
    int execute(String text);

    CommandType getCommandType();

}