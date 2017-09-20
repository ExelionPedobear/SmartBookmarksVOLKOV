package com.example.ruslanmanca.smartbookmarksvolkov.Adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruslanmanca.smartbookmarksvolkov.Models.Book;
import com.example.ruslanmanca.smartbookmarksvolkov.Models.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class CommentsAdapter {
    public static final String TABLE = "comments";

    private SQLiteDatabase db = null;

    public CommentsAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Comment> getAll(){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "bookId", "comment", "date"}, //colonnes
                null, //selection (where)
                null, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments = this.fillCommentList(cursor);

        return comments;
    }

    public ArrayList<Comment> getByBookId(int bookId){

        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "bookId", "comment", "date"}, //colonnes
                "bookId LIKE ?", //selection (where)
                new String[]{String.valueOf(bookId)}, //paramètres de sélection
                null, //group
                null, //having
                "date DESC", //orderby
                null); //limit

        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments = this.fillCommentList(cursor);

        return comments;
    }

    public long insert(long bookId, String comment){
        long id = -1;
        try {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            ContentValues contentValue = new ContentValues();
            contentValue.put("bookId", bookId);
            contentValue.put("comment", comment);
            contentValue.put("date", formattedDate);
            id = db.insert(TABLE, null, contentValue);

            return id;
        }
        catch(Exception ex){
            return id;
        }
    }

    public long getCount(){
        long count  = DatabaseUtils.queryNumEntries(db, TABLE);
        return count;
    }

    private ArrayList<Comment> fillCommentList(Cursor cursor){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        BooksAdapter ba = new BooksAdapter(db);
        while (cursor.moveToNext()){
            Comment comment = new Comment();
            int idColumnIndex = cursor.getColumnIndex("id");
            int bookIdColumnIndex = cursor.getColumnIndex("bookId");
            int commentColumnIndex = cursor.getColumnIndex("comment");
            int dateColumnIndex = cursor.getColumnIndex("date");

            comment.setId(cursor.getLong(idColumnIndex));
            comment.setBookId(cursor.getLong(bookIdColumnIndex));
            comment.setComment(cursor.getString(commentColumnIndex));
            comment.setDate(cursor.getString(dateColumnIndex));

            Book book = new Book();
            comment.setBook(ba.getById(comment.getBookId()));

            comments.add(comment);
        }

        return comments;
    }
}
