package com.tripletres.scrapperapp.chat.bot;

import com.tripletres.scrapperapp.data.Message;

/**
 * Main definitions for bot
 * Created by Daniel on 21/07/2017.
 */

public interface ChatBotContract {
    void init();

    void sendMessage();

    void destroy();

    Message createRandomMessage();
}
