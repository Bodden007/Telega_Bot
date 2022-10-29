package org.example.bot;

import lombok.extern.log4j.Log4j2;
import org.example.command.CommandContainer;
import org.example.command.NoCommand;
import org.example.service.SendBotMessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.example.command.CommandName.NO;

@Component
@Log4j2
public class TelegramBot extends TelegramLongPollingBot {

// Токен из переменных сред
    final private String BOT_TOKEN = System.getenv("BOT_TOKEN");
    public static String COMMAND_PREFIX = "/";

    @Override
    public String getBotUsername() {

        return "EditorForeverBot";
    }

    @Override
    public String getBotToken() {

        return BOT_TOKEN;
    }

    private final CommandContainer commandContainer;

    public TelegramBot(){
        this.commandContainer = new CommandContainer(new SendBotMessageService(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (text.startsWith(COMMAND_PREFIX)){
                String commandIdentifier =  text.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);

            }else {

                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);

            }
        }else if (update.hasCallbackQuery()){

            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long callChatId = update.getCallbackQuery().getMessage().getChatId();
            if (callData.equals("one")){
                String answer = "ПРИВЕТ ЧУВАК";
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setChatId(callChatId);
                editMessageText.setMessageId((int) messageId);
                editMessageText.setText(answer);

                try {
                    execute(editMessageText);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }

            }

        }
    }


    public void telegramBot (){

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        log.info("TelegramController Ok");
    }

}
