package com.gt.fjbatresv.devs502.main.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gt.fjbatresv.devs502.R;
import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by javie on 29/03/2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private List<Message> messages;
    private Context context;
    private ImageLoader loader;

    private String userUid;

    public MessagesAdapter(List<Message> messages, Context context, ImageLoader loader) {
        this.messages = messages;
        this.context = context;
        this.loader = loader;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (message.getRemitenteUid().equals(userUid)) {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        holder.message.setText(message.getMensaje());
        holder.userName.setText(message.getRemitente());
        if (message.getRemitentePhoto() != null) {
            loader.load(holder.userAvater, message.getRemitentePhoto());
        } else {
            holder.userAvater.setImageResource(R.drawable.ic_account_circle_white_48dp);
        }
    }

    public void add(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    public void clear() {
        messages.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.userAvatar)
        CircleImageView userAvater;
        @Bind(R.id.userName)
        TextView userName;
        @Bind(R.id.message)
        TextView message;
        @Bind(R.id.container)
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
