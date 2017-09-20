package com.example.ruslanmanca.smartbookmarksvolkov;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.BooksAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.CommentsAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.DatabaseAdapter;

public class MainActivity extends AppCompatActivity {

    Button btnBooks;
    Button btnComments;
    Button btnCreateComment;

    TextView tvBookCount;
    TextView tvCommentCount;
    TextView txAverageCommentsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBooks = (Button)findViewById(R.id.btnBooks);
        btnComments = (Button)findViewById(R.id.btnComments);
        btnCreateComment = (Button)findViewById(R.id.btnCreateComment);

        tvBookCount = (TextView)findViewById(R.id.tvBookCount);
        tvCommentCount = (TextView)findViewById(R.id.tvCommentCount);
        txAverageCommentsCount = (TextView)findViewById(R.id.txAverageCommentsCount);

        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BooksActivity.class);
                startActivity(intent);
            }
        });

        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });


        btnCreateComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateCommentActivity.class);
                startActivity(intent);
            }
        });

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        BooksAdapter booksAdapter = new BooksAdapter(db);
        CommentsAdapter commentsAdapter = new CommentsAdapter(db);

        tvBookCount.setText(String.valueOf(booksAdapter.getCount()));
        tvCommentCount.setText(String.valueOf(commentsAdapter.getCount()));
        txAverageCommentsCount.setText(String.valueOf(booksAdapter.getAverageNumberOfComments()));
    }
}
