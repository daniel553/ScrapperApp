package com.tripletres.scrapperapp.chat;

import android.os.Bundle;
import android.view.MenuItem;

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
        /**
         * Initial message loader
         */
        void loadMessages();

        /**
         * Save a new message
         *
         * @param message - {@link Message}
         */
        void saveMessage(Message message);

        /**
         * Gets an embedded content for a message
         *
         * @param message
         */
        void getEmbedded(Message message);

        /**
         * Attaches embed content to a message
         *
         * @param message
         * @param embedded
         */
        void attachEmbedded(Message message, Embedded embedded);

        /**
         * Menu option selected
         *
         * @param item {@link MenuItem}
         */
        void onOptionsItemSelected(MenuItem item);

        /**
         * Get a resource string from id
         *
         * @param id - string id
         * @return
         */
        String getString(int id);

        /**
         * Initializes bot
         */
        void initBot();

        /**
         * Stops bot
         */
        void stopBot();
    }

    interface View extends BaseView<Presenter> {
        /**
         * Binds control of UI
         *
         * @param root - {@link View}
         */
        void bindUI(android.view.View root);

        /**
         * Binds fragment data
         *
         * @param args
         */
        void bindData(Bundle args);

        /**
         * Displays messages on list
         *
         * @param messages
         */
        void showMessages(RealmResults<Message> messages);

        /**
         * Save any message
         *
         * @param message
         */
        void saveMessage(Message message);

        /**
         * Add a new message to DB
         */
        void addNewMessage();

        /**
         * Shows error if ocurrs
         *
         * @param id message error id
         */
        void showError(int id);

        /**
         * Reload messages on list
         */
        void reloadMessages();

        /**
         * List item message clicked
         *
         * @param pos - Position of clicked item
         */
        void messageClicked(int pos);

        /**
         * Gets a String from resouces
         *
         * @param id
         * @return
         */
        String getString(int id);

        /**
         * Initializes the bot
         */
        void initBot();

    }


}
