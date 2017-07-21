package com.tripletres.scrapperapp.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tripletres.scrapperapp.Injection;
import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.util.ActivityUtil;

/**
 * Chat activity class.
 * Displays messages as a Social network does
 */
public class ChatActivity extends AppCompatActivity {

    private ChatPresenter mChatPresenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        //Fragment chat
        ChatFragment chatFragment = (ChatFragment)
                getFragmentManager().findFragmentById(R.id.activity_chat_fragment_holder);
        if (chatFragment == null) {
            chatFragment = ChatFragment.newInstance();
            ActivityUtil.addFragment(getFragmentManager(), chatFragment,
                    R.id.activity_chat_fragment_holder);
        }

        //Presenter
        mChatPresenter = new ChatPresenter(Injection.provideChatRepository(getApplicationContext()), chatFragment);
    }
}
