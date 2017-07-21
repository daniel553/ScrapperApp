package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;
import com.tripletres.scrapperapp.util.LogUtil;

import io.realm.RealmResults;

/**
 * Chat main repsitory
 * Created by Daniel on 20/07/2017.
 */

public class ChatRepository implements ChatDataSourceContract {
    public static final String TAG = ChatRepository.class.getName();

    private static ChatRepository INSTANCE = null;

    private final ChatDataSource mChatDatasource;

    private ChatRepository(ChatDataSource chatDataSource) {
        mChatDatasource = chatDataSource;
    }

    public static ChatRepository getInstance(ChatDataSource chatDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ChatRepository(chatDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getMessages(final LoadCallback callback) {
        mChatDatasource.getMessages(new LoadCallback() {
            @Override
            public void onMessagesLoaded(RealmResults<Message> messages) {
                LogUtil.d(TAG, "Msgs: " + messages.size());
                callback.onMessagesLoaded(messages);
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void getMessage(final GetMessageCallback callback) {

    }

    @Override
    public void seedMessages(final LoadCallback callback) {
        mChatDatasource.seedMessages(new LoadCallback() {
            @Override
            public void onMessagesLoaded(RealmResults<Message> messages) {
                LogUtil.d(TAG, "Msgs: " + messages.size());
                callback.onMessagesLoaded(messages);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void saveMessage(Message message, final SaveMessageCallback callback) {
        mChatDatasource.saveMessage(message, new SaveMessageCallback() {
            @Override
            public void onMessageSaved(Message message) {
                LogUtil.d(TAG, message.getBody());
                callback.onMessageSaved(message);
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public void setEmbeddedToMessage(Message message, Embedded embedded, final SaveMessageCallback callback) {
        mChatDatasource.setEmbeddedToMessage(message, embedded, new SaveMessageCallback() {
            @Override
            public void onMessageSaved(Message message) {
                callback.onMessageSaved(message);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void clearMessages(final ClearMessages callback) {
        mChatDatasource.clearMessages(new ClearMessages() {
            @Override
            public void onMessagesClear() {
                callback.onMessagesClear();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void getEmbedded(String url, final EmbeddedCallback callback) {
        mChatDatasource.getEmbedded(url, new EmbeddedCallback() {
            @Override
            public void onSuccess(Embedded embedded) {
                LogUtil.d(TAG, embedded.toString());
                callback.onSuccess(embedded);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }
}
