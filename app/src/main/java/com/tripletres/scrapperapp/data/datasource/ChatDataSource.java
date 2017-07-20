package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.util.AppUtil;
import com.tripletres.scrapperapp.util.RealmUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Chat data source.
 * For local messages saved in realm
 * Created by Daniel on 20/07/2017.
 */

public class ChatDataSource implements ChatDataSourceContract {
    private static ChatDataSource INSTANCE = null;


    private ChatDataSource() {
    }

    public static ChatDataSource getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ChatDataSource();
        return INSTANCE;
    }

    @Override
    public void getMessages(LoadCallback callback) {

    }

    @Override
    public void getMessage(GetMessageCallback callback) {

    }

    @Override
    public void seedMessages(LoadCallback callback) {
        Realm realm = null;
        if (AppUtil.DEBUG) {
            try {
                realm = RealmUtil.getInstance();
                List<Message> messages = new ArrayList<>(10);
                realm.beginTransaction();
                for (int i = 0; i < 10; i++) {
                    Message msg = new Message();
                    msg.setBody("Body " + i);
                    msg.setSender("Sender " + i);
                    messages.add(realm.copyToRealm(msg));
                }
                realm.commitTransaction();
                callback.onMessagesLoaded(messages);
            } finally {
                if (realm != null)
                    realm.close();
            }
        }
    }
}
