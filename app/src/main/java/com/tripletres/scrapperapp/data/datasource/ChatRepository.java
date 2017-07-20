package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
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
}
