package com.tripletres.scrapperapp.chat;

import android.text.TextUtils;
import android.view.MenuItem;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.chat.bot.ChatBot;
import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.ChatDataSourceContract;
import com.tripletres.scrapperapp.data.datasource.ChatRepository;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;
import com.tripletres.scrapperapp.util.FormatUtil;

import io.realm.RealmResults;

/**
 * Chat Presenter class.
 * Created by Daniel on 20/07/2017.
 */

public class ChatPresenter implements ChatContract.Presenter {

    private static final String TAG = ChatPresenter.class.getName();

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
        mChatRepository.getMessages(new ChatDataSourceContract.LoadCallback() {
            @Override
            public void onMessagesLoaded(RealmResults<Message> messages) {
                //Refresh view
                mChatView.showMessages(messages);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void saveMessage(Message message) {
        if (message != null && !TextUtils.isEmpty(message.getBody()))
            mChatRepository.saveMessage(message, new ChatDataSourceContract.SaveMessageCallback() {
                @Override
                public void onMessageSaved(Message message) {
                    //Reload after save
                    mChatView.reloadMessages();
                    if (message.isWay())
                        mChatView.clearInput();
                    getEmbedded(message);
                }

                @Override
                public void onError() {
                    mChatView.showError(R.string.error_saving_message);
                }
            });
    }

    @Override
    public void getEmbedded(final Message message) {
        final String url = FormatUtil.getUrl(message.getBody());
        if (url != null)
            mChatRepository.getEmbedded(url, new ChatDataSourceContract.EmbeddedCallback() {
                @Override
                public void onSuccess(Embedded embedded) {
                    attachEmbedded(message, embedded);
                }

                @Override
                public void onError() {

                }
            });
    }

    @Override
    public void attachEmbedded(Message message, Embedded embedded) {
        //If embedded has no content, we do not need to save it.
        if (embedded != null && embedded.title != null)
            mChatRepository.setEmbeddedToMessage(message, embedded,
                    new ChatDataSourceContract.SaveMessageCallback() {
                        @Override
                        public void onMessageSaved(Message message) {
                        }

                        @Override
                        public void onError() {
                        }
                    });
    }

    @Override
    public void onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_chat_clear:
                mChatRepository.clearMessages(new ChatDataSourceContract.ClearMessages() {
                    @Override
                    public void onMessagesClear() {
                        mChatView.reloadMessages();
                    }

                    @Override
                    public void onError() {
                        mChatView.showError(R.string.error_deleting_message);
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public String getString(int id) {
        return mChatView.getString(id);
    }

    @Override
    public void initBot() {
        ChatBot.getInstance(this).init();
    }

    @Override
    public void stopBot() {
        ChatBot.getInstance(this).destroy();
    }


}
