package com.tripletres.scrapperapp.chat;

import android.os.Bundle;

import com.tripletres.scrapperapp.BasePresenter;
import com.tripletres.scrapperapp.BaseView;
import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;

import io.realm.RealmResults;

/**
 * Defines the chat presenter and view contract
 * Created by Daniel on 20/07/2017.
 */

public interface ChatContract {

    interface Presenter extends BasePresenter {
        void loadMessages();

        void saveMessage(Message message);

        void getEmbedded(Message message);

        void attachEmbedded(Message message, Embedded embedded);
    }

    interface View extends BaseView<Presenter> {
        void bindUI(android.view.View root);

        void bindData(Bundle args);

        void showMessages(RealmResults<Message> messages);

        void saveMessage(Message message);

        void addNewMessage();

        void showError(int id);

        void reloadMessages();

    }


}
