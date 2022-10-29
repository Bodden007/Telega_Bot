package org.example;


import org.example.bot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "/properties")
public class InjectionContext {

    @Bean
    public TelegramBot telegramBotController ()  {

        return new TelegramBot();
    }

}
