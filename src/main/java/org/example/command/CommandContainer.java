package org.example.command;

import com.google.common.collect.ImmutableMap;
import org.example.service.SendBotMessage;

import static org.example.command.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command uknownCommand;

    public CommandContainer(SendBotMessage sendBotMessage){
     commandMap = ImmutableMap.<String, Command> builder()
             .put(START.getCommandName(), new StartCommand(sendBotMessage))
             .put(STOP.getCommandName(), new StopCommand(sendBotMessage))
             .put(NO.getCommandName(), new NoCommand(sendBotMessage))
             .build();

     uknownCommand = new UknownCommand(sendBotMessage);

    }

    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier, uknownCommand);
    }

}
