package com.r.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books){
        this.books = books;

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.book_list_item,parent,false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        String authors = "";
        int i= 0;
        for(String author : book.getAuthors()){
            authors += author;
            i++;
            if(i < book.getAuthors().length){
                authors +=", ";
            }
        }
        holder.author.setText(authors);
        holder.publisher.setText(book.getPublisher());
        holder.publishedDate.setText(book.getPublishedDate());


    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView publisher;
        TextView publishedDate;


        public BookViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.bookTitle);
            author = view.findViewById(R.id.bookAuthor);
            publisher = view.findViewById(R.id.bookPublisher);
            publishedDate = view.findViewById(R.id.bookPublishedDate);

        }
    }
}
