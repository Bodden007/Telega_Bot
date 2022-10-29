package org.example.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),

    NO("/nocommand");

    final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName(){
        return commandName;
    }
}
