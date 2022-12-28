package com.ognevoydev.springechobot.handler.impl;

import com.ognevoydev.springechobot.exception.EmptyUpdateException;
import com.ognevoydev.springechobot.handler.api.CommandHandler;
import com.ognevoydev.springechobot.handler.api.UpdateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.ognevoydev.springechobot.constant.Commands.START;

@Slf4j
@Component
public class UpdateHandlerImpl implements UpdateHandler {

    @Autowired
    private CommandHandler commandHandler;

    @Override
    public Optional<SendMessage> handleUpdate(Update update) {
        Optional<SendMessage> answer = Optional.empty();
        try {
            answer = handle(update);
        }
        catch (EmptyUpdateException e) {
            log.error("Error occurred: " + e.getMessage());
        }
        return answer;
    }

    private Optional<SendMessage> handle(Update update) throws EmptyUpdateException {
        if(isUpdateNotEmpty(update)) {
            String messageText = update.getMessage().getText();
            String name = update.getMessage().getChat().getFirstName();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case START:
                    return Optional.of(commandHandler.startCommandReceived(chatId, name));
                default:
                    return Optional.of(commandHandler.unknownCommandReceived(chatId, messageText));
            }
        }
        else {
            throw new EmptyUpdateException("Empty update received");
        }
    }

    private boolean isUpdateNotEmpty(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

}
