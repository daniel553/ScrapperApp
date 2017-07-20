package com.tripletres.scrapperapp.data.datasource;

import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.util.AppUtil;
import com.tripletres.scrapperapp.util.LogUtil;
import com.tripletres.scrapperapp.util.RealmUtil;

import java.util.List;

import io.realm.Realm;

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
        Realm realm = null;
        if (AppUtil.DEBUG) {
            try {
                realm = RealmUtil.getInstance();
                if (realm.where(Message.class).count() <= 0)
                    seedMessages(callback);
            } finally {
                if (realm != null)
                    realm.close();
            }
        }
    }

    @Override
    public void getMessage(final GetMessageCallback callback) {

    }

    @Override
    public void seedMessages(final LoadCallback callback) {
        mChatDatasource.seedMessages(new LoadCallback() {
            @Override
            public void onMessagesLoaded(List<Message> messages) {
                LogUtil.d(TAG, "Msgs: " + messages.size());
                callback.onMessagesLoaded(messages);
            }

            @Override
            public void onError() {

            }
        });
    }
}
