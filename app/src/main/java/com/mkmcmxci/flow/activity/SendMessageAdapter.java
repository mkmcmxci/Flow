package com.mkmcmxci.flow.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Message;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.ui.flow.AnswerAdapter;

import java.util.List;

public class SendMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENDER = 0;
    private static final int TYPE_RECEIVER = 1;

    Context mContext;
    List<Message> mMessageList;
    int senderID;

    public SendMessageAdapter(Context context, List<Message> list, int senderID) {
        this.mContext = context;
        this.mMessageList = list;
        this.senderID = senderID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == TYPE_SENDER) {

            View headerView = LayoutInflater.from(mContext).inflate(R.layout.fragment_message_reply_sender_row, parent, false);

            return new SendMessageAdapter.MessageSenderViewHolder(headerView);

        } else if (viewType == TYPE_RECEIVER) {

            View rowView = LayoutInflater.from(mContext).inflate(R.layout.fragment_message_reply_receiver_row, parent, false);

            return new SendMessageAdapter.MessageReceiverViewHolder(rowView);

        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MessageSenderViewHolder) {
            MessageSenderViewHolder senderViewHolder = (MessageSenderViewHolder) holder;

            String senderDate = mMessageList.get(position).getDate();

            senderDate = senderDate.substring(11, 16);

            senderViewHolder.date.setText(senderDate);
            senderViewHolder.reply.setText(mMessageList.get(position).getReply());
            senderViewHolder.username.setText(mMessageList.get(position).getUsername());

        } else if (holder instanceof MessageReceiverViewHolder) {
            MessageReceiverViewHolder receiverViewHolder = (MessageReceiverViewHolder) holder;

            String receiverDate = mMessageList.get(position).getDate();

            receiverDate = receiverDate.substring(11, 16);

            receiverViewHolder.date.setText(receiverDate);
            receiverViewHolder.reply.setText(mMessageList.get(position).getReply());
            receiverViewHolder.username.setText(mMessageList.get(position).getUsername());

        }

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {

        SessionManagement session = new SessionManagement(mContext);

        if (mMessageList.get(position).getUsername().equals(session.loadUsername())) {
            return TYPE_SENDER;
        }
        return TYPE_RECEIVER;
    }


    public class MessageSenderViewHolder extends RecyclerView.ViewHolder {

        TextView reply, username, date;

        public MessageSenderViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.fragment_message_reply_sender_row_date);
            reply = itemView.findViewById(R.id.fragment_message_reply_sender_row_content);
            username = itemView.findViewById(R.id.fragment_message_reply_sender_row_username);

        }
    }

    public class MessageReceiverViewHolder extends RecyclerView.ViewHolder {

        TextView reply, username, date;

        public MessageReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.fragment_message_reply_receiver_row_date);
            reply = itemView.findViewById(R.id.fragment_message_reply_receiver_row_content);
            username = itemView.findViewById(R.id.fragment_message_reply_receiver_row_username);

        }
    }
}
