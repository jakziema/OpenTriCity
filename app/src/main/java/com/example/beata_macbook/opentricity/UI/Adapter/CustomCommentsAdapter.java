package com.example.beata_macbook.opentricity.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Comment;
import com.example.beata_macbook.opentricity.UI.Utils.EmailSender;

import java.util.ArrayList;

/**
 * Created by alazelewska on 08.11.16.
 */

public class CustomCommentsAdapter extends BaseAdapter{

    private ListView mListView;
    private ArrayList<Comment> data;
    private LayoutInflater inflater;
    private Activity activity;


    public CustomCommentsAdapter(ListView listView, ArrayList<Comment> data, Activity activity) {
        this.mListView = listView;
        this.data = data;
        this.inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        RowViewHolder holder = new RowViewHolder();
        if (convertView == null) {
            view = inflater.inflate(R.layout.single_comment_row, null);
            holder.mButton = (Button) view.findViewById(R.id.commentAbuseBtn);
            holder.mText = (TextView) view.findViewById(R.id.commentText);

            holder.mButton.setOnClickListener(abuseBtnClickListener);

            view.setTag(holder);
        } else {
            holder = (RowViewHolder) view.getTag();
        }
        if(!data.isEmpty())
        {
            holder.mText.setText( data.get(position).getText() );
            holder.mText.setTag( data.get(position).getId() );

            view.setOnClickListener(abuseBtnClickListener);
        }
        return view;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListView.getChildAt(position).getId();
    }

    public void add(final Comment comment) {
        data.add(comment);
        notifyDataSetChanged();
    }


    private View.OnClickListener abuseBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int position = mListView.getPositionForView((View) v.getParent());
            Comment comment = ((Comment) mListView.getItemAtPosition(position));
            final StringBuilder message = new StringBuilder("Zgłoszono komentarz o treści: ");
            message.append(comment.getText());
            message.append(". ID komentarza: ");
            message.append(comment.getId());
            new EmailSender(activity).sendEmail(
                    "alazelewska.47@gmail.com", "OpenTriCity zgłoszenie", message.toString()
            );
        }
    };

    protected static class RowViewHolder {
        public Button mButton;
        public TextView mText;
    }

}