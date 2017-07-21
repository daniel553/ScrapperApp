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

    void getMessages(LoadCallback callback);

    void getMessage(GetMessageCallback callback);

    void seedMessages(LoadCallback callback);

    void saveMessage(Message message, SaveMessageCallback callback);

    void setEmbeddedToMessage(Message message, Embedded embedded, SaveMessageCallback callback);

    void clearMessages(ClearMessages callback);

    //Retrofit
    void getEmbedded(String url, EmbeddedCallback callback);
}
