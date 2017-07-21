package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;

import io.realm.RealmResults;

/**
 * Chat data source interface.
 * Defines main methods for data source
 * Created by Daniel on 20/07/2017.
 */

public interface ChatDataSourceContract {

    //CALLBACKS
    interface LoadCallback {
        void onMessagesLoaded(RealmResults<Message> messages);

        void onError();
    }

    interface GetMessageCallback {
        void onMessageLoaded(Message message);

        void onError();
    }

    interface SaveMessageCallback {
        void onMessageSaved(Message message);

        void onError();
    }

    interface EmbeddedCallback {
        void onSuccess(Embedded embedded);

        void onError();
    }

    interface ClearMessages {
        void onMessagesClear();

        void onError();
    }


    //MAIN METHODS

    /**
     * Gets all messages on database
     *
     * @param callback
     */
    void getMessages(LoadCallback callback);

    /**
     * Get a single message
     *
     * @param callback
     */
    void getMessage(GetMessageCallback callback);

    /**
     * Seed messages on db
     *
     * @param callback
     */
    void seedMessages(LoadCallback callback);

    /**
     * Saves a new message on DB
     *
     * @param message
     * @param callback
     */
    void saveMessage(Message message, SaveMessageCallback callback);

    /**
     * Sets embedded to any message
     *
     * @param message  - {@link Message} target
     * @param embedded - {@link Embedded} data
     * @param callback
     */
    void setEmbeddedToMessage(Message message, Embedded embedded, SaveMessageCallback callback);

    /**
     * Clears all messages in db
     *
     * @param callback
     */
    void clearMessages(ClearMessages callback);

    //Retrofit

    /**
     * Get embedded data from URL
     *
     * @param url      - valid url
     * @param callback
     */
    void getEmbedded(String url, EmbeddedCallback callback);
}
