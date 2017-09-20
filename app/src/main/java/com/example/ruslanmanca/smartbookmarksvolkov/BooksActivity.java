package com.example.ruslanmanca.smartbookmarksvolkov;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.BooksAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.DatabaseAdapter;
import com.example.ruslanmanca.smartbookmarksvolkov.Adapters.ListViewAdapterBooks;
import com.example.ruslanmanca.smartbookmarksvolkov.Models.Book;

import java.util.ArrayList;

public class BooksActivity extends AppCompatActivity {

    ArrayList<Book> books;
    ListViewAdapterBooks customAdapter;
    ListView lvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        books = new ArrayList<Book>();

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        //Demande au Helper une connexion en lecture seule
        //SQLiteDatabase db = maCollecDb.getReadableDatabase();
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        BooksAdapter booksAdapter = new BooksAdapter(db);
        books = booksAdapter.getAll();

        customAdapter = new ListViewAdapterBooks(books, this);

        lvBooks.setAdapter(customAdapter);
    }
}
