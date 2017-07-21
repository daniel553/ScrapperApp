package com.tripletres.scrapperapp.chat;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tripletres.scrapperapp.R;
import com.tripletres.scrapperapp.data.Message;
import com.tripletres.scrapperapp.data.datasource.remote.Embedded;

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
        Message message = (Message) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_single, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.mImageSender = (ImageView) convertView.findViewById(R.id.item_message_single_image);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.item_message_single_sender);
            viewHolder.mBody = (TextView) convertView.findViewById(R.id.item_message_single_body);

            //Embed
            viewHolder.mEmbedLayout = (RelativeLayout) convertView.findViewById(R.id.item_message_single_embed);
            viewHolder.mTitle = (TextView) convertView.findViewById(R.id.item_message_single_emb_title);
            viewHolder.mSubtitle = (TextView) convertView.findViewById(R.id.item_message_single_emb_subtitle);
            viewHolder.mImageEmbed = (ImageView) convertView.findViewById(R.id.item_message_single_emb_image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mName.setText(message.getSender());
        viewHolder.mBody.setText(message.getBody());
        Embedded embedded = message.getEmbedded();
        if (embedded != null) {
            viewHolder.mEmbedLayout.setVisibility(View.VISIBLE);
            if (embedded.provider_name != null)
                viewHolder.mTitle.setText(embedded.provider_name);
            if (embedded.title != null)
                viewHolder.mSubtitle.setText(embedded.title);
            if (embedded.thumbnail_url != null && !TextUtils.isEmpty(embedded.thumbnail_url)) {
                //TODO: call image loader
            }

        } else {
            viewHolder.mEmbedLayout.setVisibility(View.GONE);
        }

        return convertView;
    }

    /**
     * Holder
     */
    private static class ViewHolder {
        ImageView mImageSender;
        TextView mName;
        TextView mBody;

        //Embed
        RelativeLayout mEmbedLayout;
        TextView mTitle;
        TextView mSubtitle;
        ImageView mImageEmbed;
    }
}
