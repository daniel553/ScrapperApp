package com.tripletres.scrapperapp.chat;

import android.os.Bundle;

import com.tripletres.scrapperapp.BasePresenter;
import com.tripletres.scrapperapp.BaseView;
import com.tripletres.scrapperapp.data.Message;

import java.util.List;

/**
 * Defines the chat presenter and view contract
 * Created by Daniel on 20/07/2017.
 */

public interface ChatContract {

    interface Presenter extends BasePresenter {
        void loadMessages();
    }

    interface View extends BaseView<Presenter> {
        void bindUI(android.view.View root);

        void bindData(Bundle args);

        void showMessages(List<Message> messages);
    }


}
