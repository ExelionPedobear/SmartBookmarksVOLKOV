package com.example.ruslanmanca.smartbookmarksvolkov;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.CommentsAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.DatabaseAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.ListViewAdapterComments;
import com.example.ruslanmanca.smartbookmarksvolkov.Models.Comment;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    ArrayList<Comment> comments;
    ListViewAdapterComments customAdapter;
    ListView lvComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        lvComments = (ListView) findViewById(R.id.lvComments);
        comments = new ArrayList<Comment>();

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        //Demande au Helper une connexion en lecture seule
        //SQLiteDatabase db = maCollecDb.getReadableDatabase();
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        CommentsAdapter commentsAdapter = new CommentsAdapter(db);
        comments = commentsAdapter.getAll();

        customAdapter = new ListViewAdapterComments(comments, this);

        lvComments.setAdapter(customAdapter);
    }
}
