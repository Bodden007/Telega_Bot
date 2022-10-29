package org.example.command;

import org.example.service.SendBotMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "ХУЙ пойми чо ты хочешь??";

    private final SendBotMessage sendBotMessage;

    public UknownCommand(SendBotMessage sendBotMessage){
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(Long.valueOf(update.getMessage().getChatId().toString()),UNKNOWN_MESSAGE);
    }
}
