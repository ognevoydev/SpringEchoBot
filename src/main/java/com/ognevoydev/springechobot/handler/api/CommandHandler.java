package com.ognevoydev.springechobot.handler.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandHandler {
    public SendMessage startCommandReceived(long chatId, String name);
    public SendMessage unknownCommandReceived(long chatId, String message);
}
