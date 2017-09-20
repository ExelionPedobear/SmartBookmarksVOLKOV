package com.example.ruslanmanca.smartbookmarksvolkov.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ruslanmanca.smartbookmarksvolkov.Models.Book;
import com.example.ruslanmanca.smartbookmarksvolkov.R;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class ListViewAdapterBooks extends BaseAdapter {
    ArrayList<Book> data;
    Context context;
    LayoutInflater layoutInflater;

    public ListViewAdapterBooks(ArrayList<Book> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Book getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder = null;

        if(convertView == null)
        {
            vi = layoutInflater.inflate(R.layout.listview_books_row, null);
            holder = new ViewHolder((TextView) vi.findViewById(R.id.tvTitle),
                    (TextView) vi.findViewById(R.id.tvAuthor),
                    (TextView) vi.findViewById(R.id.tvGenre),
                    (TextView) vi.findViewById(R.id.tvCommentsCount));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();
        }

        Book book = getItem(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvGenre.setText(book.getGenre());
        holder.tvCommentsCount.setText(String.valueOf(book.getCommentsCount()));

        return vi;
    }
}

class ViewHolder{
    public TextView tvTitle;
    public TextView tvAuthor;
    public TextView tvGenre;
    public TextView tvCommentsCount;

    public ViewHolder(TextView tvTitle, TextView tvAuthor, TextView tvGenre, TextView tvCommentsCount){
        this.tvTitle = tvTitle;
        this.tvAuthor = tvAuthor;
        this.tvGenre = tvGenre;
        this.tvCommentsCount = tvCommentsCount;
    }
}