package com.ognevoydev.springechobot.bot;

import com.ognevoydev.springechobot.config.BotConfig;
import com.ognevoydev.springechobot.handler.api.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class SpringEchoBot extends TelegramLongPollingBot {

    @Autowired
    private UpdateHandler updateHandler;
    @Autowired
    private ChatManager chatManager;

    final BotConfig config;

    public SpringEchoBot(BotConfig config) {
        this.config = config;
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Optional<SendMessage> message = updateHandler.handleUpdate(update);
        message.ifPresent(
                value -> {
                    chatManager.sendMessage(this, value);
                }
        );
    }

}