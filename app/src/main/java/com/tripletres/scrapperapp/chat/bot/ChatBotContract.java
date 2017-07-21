package com.tripletres.scrapperapp.chat.bot;

import com.tripletres.scrapperapp.data.Message;

/**
 * Main definitions for bot
 * Created by Daniel on 21/07/2017.
 */

public interface ChatBotContract {
    /**
     * Initializes the bot
     */
    void init();

    /**
     * Bot sends a message to Database
     */
    void sendMessage();

    /**
     * Bot is destroyed
     */
    void destroy();

    /**
     * Generates a random message
     *
     * @return - {@link Message} created
     */
    Message createRandomMessage();
}
