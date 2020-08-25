package com.mkmcmxci.flow.ui.message;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.SendMessageActivity;
import com.mkmcmxci.flow.entities.Conversation;

import java.util.List;

public class MessageAdapter  extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{


    Context mContext;
    List<Conversation> mMessageList;

    public MessageAdapter(Context context, List<Conversation> list) {
        this.mContext = context;
        this.mMessageList = list;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_message_row, parent, false);

        return new MessageAdapter.MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, final int position) {

        holder.username.setText(mMessageList.get(position).getReceiver());
        holder.content.setText(mMessageList.get(position).getLastMessage());

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, SendMessageActivity.class);
                i.putExtra("Username",mMessageList.get(position).getReceiver());
                i.putExtra("UserID",mMessageList.get(position).getReceiverID());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView content, username;
        ConstraintLayout row;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            row = itemView.findViewById(R.id.my_message_row);
            content = itemView.findViewById(R.id.my_message_row_content);
            username = itemView.findViewById(R.id.my_message_row_username);
        }
    }
}
