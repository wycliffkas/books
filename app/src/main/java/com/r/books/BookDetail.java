package com.r.books;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        Book book = getIntent().getParcelableExtra("Book");

    }
}
