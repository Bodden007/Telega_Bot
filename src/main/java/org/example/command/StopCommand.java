package org.example.command;

import org.example.service.SendBotMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{

    private final SendBotMessage sendBotMessage;

    public final static String STOP_MESSAGE = "Отъебись";

    public StopCommand(SendBotMessage sendBotMessage){
        this.sendBotMessage = sendBotMessage;
    }


    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(Long.valueOf(update.getMessage().getChatId().toString()),STOP_MESSAGE);
    }
}
