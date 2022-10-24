package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.controller.TelegramBotController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@Log4j2
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(InjectionContext.class);

        TelegramBotController telegramBotController = context.getBean(TelegramBotController.class);

        telegramBotController.telegramBot();

        log.info("Main class ok");

    }
}