package org.example.command;

import org.example.service.SendBotMessage;
import org.example.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;


public class StartCommand implements Command{

    private final SendBotMessage sendBotMessage;

    public final static String START_MESSAGE = "Хули надо??";

    public StartCommand(SendBotMessage sendBotMessage){
        this.sendBotMessage = sendBotMessage;
    }

    @Override
    public void execute(Update update) {
        sendBotMessage.sendMessage(Long.valueOf(update.getMessage().getChatId().toString()),START_MESSAGE);
    }
}
