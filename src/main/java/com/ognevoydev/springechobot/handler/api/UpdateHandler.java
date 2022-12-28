package com.ognevoydev.springechobot.handler.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface UpdateHandler {
    Optional<SendMessage> handleUpdate(Update update);
}
