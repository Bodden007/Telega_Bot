package org.example.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@Log4j2
public class TelegramBotController extends TelegramLongPollingBot {
// Токен из переменных сред
    final private String BOT_TOKEN = System.getenv("BOT_TOKEN");
    @Override
    public String getBotUsername() {

        return "EditorForeverBot";
    }

    @Override
    public String getBotToken() {

        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public void telegramBot (){

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotController());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        log.info("TelegramController Ok");
    }

}
