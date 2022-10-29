package org.example.command;

import org.example.service.SendBotMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command{

    private final SendBotMessage sendBotMessage;

    public static final String NO_MESSAGE = "Не хуя не понял";

    public NoCommand (SendBotMessage sendBotMessage){
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(Long.valueOf(update.getMessage().getChatId().toString()), NO_MESSAGE);

    }
}
