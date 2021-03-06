package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;
import com.tripletres.scrapperapp.data.datasource.remote.EmbeddedDataSource;
import com.tripletres.scrapperapp.data.datasource.remote.EmbeddedDataSourceContract;
import com.tripletres.scrapperapp.util.LogUtil;
import com.tripletres.scrapperapp.util.RealmUtil;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Chat data source.
 * For local messages saved in realm
 * Created by Daniel on 20/07/2017.
 */

public class ChatDataSource implements ChatDataSourceContract {

    private static final String TAG = ChatDataSource.class.getName();
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
        Realm realm = null;
        try {
            realm = RealmUtil.getInstance();
            RealmResults<Message> messages = realm.where(Message.class).findAll();
            if (messages.size() <= 0)
                seedMessages(callback);
            else
                callback.onMessagesLoaded(messages);
        } catch (IllegalStateException ise) {
            LogUtil.e(TAG, ise.getMessage(), ise);
            callback.onError();
        }
    }

    @Override
    public void getMessage(GetMessageCallback callback) {

    }

    @Override
    public void seedMessages(LoadCallback callback) {
        Realm realm = null;
        try {
            realm = RealmUtil.getInstance();
            realm.beginTransaction();
            realm.copyToRealm(new Message("Android Bot", " Say hello to Android"));
            realm.commitTransaction();
            callback.onMessagesLoaded(realm.where(Message.class).findAll());
        } catch (IllegalStateException ise) {
            LogUtil.e(TAG, ise.getMessage(), ise);
            callback.onError();
        }
    }

    @Override
    public void saveMessage(Message message, SaveMessageCallback callback) {
        Realm realm = null;
        try {
            realm = RealmUtil.getInstance();
            realm.beginTransaction();
            Message savedMessage = realm.copyToRealm(message);
            realm.commitTransaction();
            callback.onMessageSaved(savedMessage);
        } catch (IllegalStateException ise) {
            LogUtil.e(TAG, ise.getMessage(), ise);
            callback.onError();
        }
    }

    @Override
    public void setEmbeddedToMessage(Message message, Embedded embedded, SaveMessageCallback callback) {
        Realm realm = null;
        try {
            realm = RealmUtil.getInstance();
            realm.beginTransaction();
            Embedded savedEmbedded = realm.copyToRealm(embedded);
            message.setEmbedded(savedEmbedded);
            realm.commitTransaction();
            callback.onMessageSaved(message);
        } catch (IllegalStateException ise) {
            LogUtil.e(TAG, ise.getMessage(), ise);
            callback.onError();
        }
    }

    @Override
    public void clearMessages(ClearMessages callback) {
        Realm realm = null;
        try {
            realm = RealmUtil.getInstance();
            RealmResults<Message> messages = realm.where(Message.class).findAll();
            realm.beginTransaction();
            for (Message m : messages)
                m.deleteFromRealm();
            realm.commitTransaction();
            callback.onMessagesClear();
        } catch (IllegalStateException ise) {
            LogUtil.e(TAG, ise.getMessage(), ise);
            callback.onError();
        }
    }

    @Override
    public void getEmbedded(String url, final EmbeddedCallback callback) {
        LogUtil.d(TAG, url);
        EmbeddedDataSource embeddedDataSource = new EmbeddedDataSource();
        embeddedDataSource.getEmbedded(url, new EmbeddedDataSourceContract.GetCallback() {
            @Override
            public void onSuccess(Embedded result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError() {

            }
        });
    }
}
