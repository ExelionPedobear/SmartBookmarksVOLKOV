package com.example.ruslanmanca.smartbookmarksvolkov;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.BooksAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.CommentsAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.DatabaseAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.SpinnerBooksAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Models.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CreateCommentActivity extends AppCompatActivity {
    ArrayList<Book> books;
    SpinnerBooksAdapter customAdapter;
    Spinner spBooks;
    Book book;
    Button btnSubmit;
    EditText editComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);
        book = new Book();
        spBooks = (Spinner) findViewById(R.id.spBooks);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        editComment = (EditText)findViewById(R.id.editComment);

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();
        final BooksAdapter ba = new BooksAdapter(db);
        final CommentsAdapter ca = new CommentsAdapter(db);
        books = ba.getAll();

        customAdapter = new SpinnerBooksAdapter(books, this);
        spBooks.setAdapter(customAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = (Book)spBooks.getSelectedItem();
                ca.insert(book.getId(), editComment.getText().toString());
                Intent intent = new Intent(CreateCommentActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });

        //spLoisirs.setVisibility(View.GONE);
        //Book book = (Book)spBooks.getSelectedItem();

    }
}
