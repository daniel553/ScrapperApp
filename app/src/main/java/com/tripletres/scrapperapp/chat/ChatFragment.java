package com.tripletres.scrapperapp.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.data.Message;

import io.realm.RealmResults;

/**
 * Chat fragment user interface
 * Created by Daniel on 20/07/2017.
 */

public class ChatFragment extends Fragment implements ChatContract.View {

    private ChatContract.Presenter mPresenter;

    private RealmResults<Message> mMessages;
    private ChatMessagesListAdapter mAdapter;
    private ListView mMessagesListView;

    public ChatFragment() {
    }

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        bindUI(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        bindData(getArguments());
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void bindUI(View root) {
        mMessagesListView = (ListView) root.findViewById(R.id.fragment_chat_message_list);
    }

    @Override
    public void bindData(Bundle args) {
        mPresenter.loadMessages();
    }

    @Override
    public void showMessages(RealmResults<Message> messages) {
        mMessages = messages;
        mAdapter = new ChatMessagesListAdapter(mMessages);
        mMessagesListView.setAdapter(mAdapter);
    }
}
