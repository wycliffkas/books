package com.r.books;

import android.content.Context;
import android.content.Intent;
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
        holder.author.setText(authors);
        holder.publisher.setText(book.getPublisher());
        holder.publishedDate.setText(book.getPublishedDate());


    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //to get the book that was clicked on
            int position = getAdapterPosition();
            Book selectedBook = books.get(position);
            Intent intent = new Intent(view.getContext(), BookDetail.class);
            intent.putExtra("Book", selectedBook);
            view.getContext().startActivity(intent);

        }
    }
}
