package com.r.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.r.books.ApiUtil.getJson;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressLoader = findViewById(R.id.progressBar);

        try {
            URL bookUrl = ApiUtil.buildUrl("cooking");
            new BooksQueryTask().execute(bookUrl);
        }
        catch(Exception e) {
            Log.d("Error", e.getMessage());
        }

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
            TextView bookResults = findViewById(R.id.books_results);


            if(result == null) {

                bookResults.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this, "Error Loading data from Google API",
                        Toast.LENGTH_LONG).show();
                progressLoader.setVisibility(View.GONE);
            }
            else {

                progressLoader.setVisibility(View.GONE);

            }

            ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
            String resultString = "";
            for(Book book:books){
                resultString = resultString + book.title + "\n" +
                        book.publishedDate + "\n\n";
                bookResults.setText(resultString);
            }


        }
    }
}
