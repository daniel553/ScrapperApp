package com.tripletres.scrapperapp;

import android.content.Context;

import com.tripletres.scrapperapp.data.datasource.ChatDataSource;
import com.tripletres.scrapperapp.data.datasource.ChatRepository;
import com.tripletres.scrapperapp.util.RealmUtil;

/**
 * Repository injector.
 * Depending on a flavor (debug, prod, test) a repository Datasource is provided.
 * Created by Daniel on 20/07/2017.
 */

public class Injection {
    /**
     * Instantiates a data source of chat repository.
     *
     * @param context
     * @return
     */
    public static ChatRepository provideChatRepository(Context context) {
        RealmUtil.setConfig(context);
        ChatDataSource chatDataSourceContract = ChatDataSource.getInstance();
        return ChatRepository.getInstance(chatDataSourceContract);
    }
}
