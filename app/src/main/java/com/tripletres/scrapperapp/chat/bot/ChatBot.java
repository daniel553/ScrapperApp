package com.tripletres.scrapperapp.chat.bot;

import android.os.Handler;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.chat.ChatPresenter;
import com.tripletres.scrapperapp.data.Message;

import java.util.Random;

/**
 * Just to chat
 * Created by Daniel on 21/07/2017.
 */

public class ChatBot implements ChatBotContract {

    private static ChatBot INSTANCE = null;
    private ChatPresenter mPresenter = null;
    private Handler handler = null;
    private static int SECONDS = 6 * 1000;

    private ChatBot(ChatPresenter presenter) {
        mPresenter = presenter;
    }

    public static ChatBot getInstance(ChatPresenter presenter) {
        if (INSTANCE == null)
            INSTANCE = new ChatBot(presenter);

        return INSTANCE;
    }

    /**
     * Runnable to send a message in some SECONDS
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sendMessage();
            if (handler != null)
                handler.postDelayed(runnable, SECONDS);
        }
    };


    @Override
    public void init() {
        handler = new Handler();
        handler.postDelayed(runnable, SECONDS);
    }

    @Override
    public void sendMessage() {
        Random random = new Random();
        double val = random.nextDouble();
        if (val < 0.3)
            mPresenter.saveMessage(createRandomMessage());
    }

    @Override
    public void destroy() {
        if (handler != null)
            handler.removeCallbacks(runnable);
    }

    @Override
    public Message createRandomMessage() {
        Random random = new Random();
        String msg = "";
        double val = random.nextDouble();
        if (val <= 0.2)
            msg = mPresenter.getString(R.string.bot_1);
        else if (val > 0.2 && val <= 0.4)
            msg = mPresenter.getString(R.string.bot_2);
        else if (val > 0.4 && val <= 0.6)
            msg = mPresenter.getString(R.string.bot_3);
        else if (val > 0.6 && val <= 0.8)
            msg = mPresenter.getString(R.string.bot_4);
        else if (val > 0.0 && val <= 1)
            msg = mPresenter.getString(R.string.bot_5);

        return new Message("Android bot", msg);
    }
}
