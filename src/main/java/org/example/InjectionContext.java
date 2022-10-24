package org.example;


import org.example.controller.TelegramBotController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "/properties")
public class InjectionContext {

    @Bean
    public TelegramBotController telegramBotController ()  {

        return new TelegramBotController();
    }

}
