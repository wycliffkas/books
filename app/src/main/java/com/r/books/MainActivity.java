package com.r.books;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.r.books.ApiUtil.getJson;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressBar progressLoader;
    private RecyclerView booksRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressLoader = findViewById(R.id.progressBar);
        booksRecyclerView = findViewById(R.id.mRecyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        booksRecyclerView.setLayoutManager(linearLayoutManager);


        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);
        }
        catch(Exception e) {
            Log.d("Error", e.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.book_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.bookSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        try {
            URL bookUrl = ApiUtil.buildUrl(query);
            new BooksQueryTask().execute(bookUrl);

        }
        catch(Exception e){
            Log.d("error", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public class BooksQueryTask extends AsyncTask<URL, Void ,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressLoader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String result = null;

            try {
                result = getJson(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if(result == null) {
                progressLoader.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error Loading data from Google API",
                        Toast.LENGTH_LONG).show();
            }
            else {
                progressLoader.setVisibility(View.GONE);
                ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
                BookAdapter adapter = new BookAdapter(books);
                booksRecyclerView.setAdapter(adapter);
            }


        }
    }
}
