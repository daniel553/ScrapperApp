package com.tripletres.scrapperapp.chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.util.ActivityUtil;

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
    private TextView mInput;

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
        mInput = (TextView) root.findViewById(R.id.fragment_chat_input);
        root.findViewById(R.id.fragment_chat_button_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMessage();
            }
        });
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

    @Override
    public void saveMessage(Message message) {
        mPresenter.saveMessage(message);
    }

    @Override
    public void addNewMessage() {
        String msg = mInput.getText().toString().trim();
        if(msg.length()>0)
            mPresenter.saveMessage(new Message("Pedro Daniel", msg));
    }

    @Override
    public void showError(int id) {
        ActivityUtil.showError(id, getActivity());
    }

    @Override
    public void reloadMessages() {
        //No need to tell realm
    }
}
