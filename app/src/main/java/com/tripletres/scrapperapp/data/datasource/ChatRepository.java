package com.tripletres.scrapperapp.data.datasource;

/**
 * Chat main repsitory
 * Created by Daniel on 20/07/2017.
 */

public class ChatRepository implements ChatDataSource {

    private static ChatRepository INSTANCE = null;

    private final ChatDataSource mChatDatasource;

    private ChatRepository(ChatDataSource chatDataSource) {
        mChatDatasource = chatDataSource;
    }

    public static ChatRepository getInstance(ChatDataSource chatDataSource){
        if(INSTANCE == null){
            INSTANCE = new ChatRepository(chatDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getMessages(LoadCallback callback) {

    }

    @Override
    public void getMessage(GetMessageCallback callback) {

    }
}
