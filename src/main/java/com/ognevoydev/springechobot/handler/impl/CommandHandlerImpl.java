package com.ognevoydev.springechobot.handler.impl;

import com.ognevoydev.springechobot.handler.api.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.ognevoydev.springechobot.constant.Replies.START_REPLY;

@Component
public class CommandHandlerImpl implements CommandHandler {

    @Override
    public SendMessage startCommandReceived(long chatId, String name) {
        return generateMessage(chatId, String.format(START_REPLY, name));
    }

    @Override
    public SendMessage unknownCommandReceived(long chatId, String message) {
        return generateMessage(chatId, message);
    }

    private SendMessage generateMessage(long chatId, String answer) {
        SendMessage message = new SendMessage();
        message.setText(answer);
        message.setChatId(chatId);
        return message;
    }

}
