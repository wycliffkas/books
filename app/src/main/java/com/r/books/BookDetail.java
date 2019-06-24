package com.r.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.r.books.databinding.BookDetailBinding;

public class BookDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        Book book = getIntent().getParcelableExtra("Book");
        BookDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.book_detail);
        binding.setBook(book);



    }
}
