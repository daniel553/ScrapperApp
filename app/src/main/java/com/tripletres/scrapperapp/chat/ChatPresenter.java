package com.tripletres.scrapperapp.chat;

import com.tripletres.scrapperapp.data.datasource.ChatRepository;

/**
 * Chat Presenter class.
 * Created by Daniel on 20/07/2017.
 */

public class ChatPresenter implements ChatContract.Presenter {

    private final ChatRepository mChatRepository;
    private final ChatContract.View mChatView;

    /**
     * Instantiates a pressenter and sets to the view
     *
     * @param chatRepository
     * @param chatView
     */
    public ChatPresenter(ChatRepository chatRepository, ChatContract.View chatView) {
        mChatRepository = chatRepository;
        mChatView = chatView;

        mChatView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadMessages() {

    }


}
