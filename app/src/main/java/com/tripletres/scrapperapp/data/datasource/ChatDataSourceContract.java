package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;

import java.util.List;

import io.realm.RealmResults;

/**
 * Chat data source interface.
 * Defines main methods for data source
 * Created by Daniel on 20/07/2017.
 */

public interface ChatDataSourceContract {
    interface LoadCallback{
        void onMessagesLoaded(RealmResults<Message> messages);
        void onError();
    }

    interface GetMessageCallback{
        void onMessageLoaded(Message message);
        void onError();
    }

    void getMessages(LoadCallback callback);
    void getMessage(GetMessageCallback callback);
    void seedMessages(LoadCallback callback);
}
