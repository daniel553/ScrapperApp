package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;

import java.util.List;

/**
 * Chat data source interface.
 * Defines main methods for data source
 * Created by Daniel on 20/07/2017.
 */

public interface ChatDataSource {
    interface LoadCallback{
        void onMessagesLoaded(List<Message> messages);
        void onError();
    }

    interface GetMessageCallback{
        void onMessageLoaded(Message message);
        void onError();
    }

    void getMessages(LoadCallback callback);
    void getMessage(GetMessageCallback callback);
}
