package com.tripletres.scrapperapp.chat;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.data.Message;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Chat message list adapter.
 * Displays a list of messages in chat
 * <p>
 * Created by Daniel on 20/07/2017.
 */

public class ChatMessagesListAdapter extends RealmBaseAdapter implements ListAdapter {

    public ChatMessagesListAdapter(@Nullable OrderedRealmCollection<Message> messages) {
        super(messages);
    }

    @Override
    public int getCount() {
        return adapterData != null ? adapterData.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_single, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.mImageSender = (ImageView) convertView.findViewById(R.id.item_message_single_image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.item_message_single_sender);
            viewHolder.mBody = (TextView) convertView.findViewById(R.id.item_message_single_body);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Message message = (Message) getItem(position);
        viewHolder.mName.setText(message.getSender());
        viewHolder.mBody.setText(message.getBody());

        return convertView;
    }

    /**
     * Holder
     */
    private static class ViewHolder {
        ImageView mImageSender;
        TextView mName;
        TextView mBody;
    }
}
