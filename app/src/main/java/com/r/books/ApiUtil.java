package com.r.books;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){

    }

    public static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    public static final String QUERY_KEY_PARAMETER = "q";


    //create a URL
    public static URL buildUrl(String title) {

        URL url = null;
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_KEY_PARAMETER, title)
                .build();

        try{
            url = new URL(uri.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return url;

    }

    //connect to the API

    public static String getJson(URL url) throws IOException {
        //create a connection to the URL

        HttpURLConnection connection = (HttpURLConnection)url.openConnection();


        try{

            //call stream to read results
            InputStream stream = connection.getInputStream();

            //convert json results from stream to string(utf)
            Scanner scanner = new Scanner(stream);

            //pattern to read everything
            scanner.useDelimiter("\\A");

            boolean hasData = scanner.hasNext();

            if(hasData){
                return scanner.next();
            }
            else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }

    }


    public static ArrayList<Book> getBooksFromJson(String json) {
        final String ID = "id";
        final String TITLE = "title";
        final String SUBTITLE = "subtitle";
        final String AUTHORS = "authors";
        final String PUBLISHER = "publisher";
        final String PUBLISHED_DATE = "publishedDate";
        final String ITEMS = "items";
        final String VOLUMEINFO = "volumeInfo";
        final String DESCRIPTION = "description";


        ArrayList<Book> books = new ArrayList<Book>();

        try {

            JSONObject jsonBooks = new JSONObject(json);
            JSONArray arrayBooks = jsonBooks.getJSONArray(ITEMS);
            int numberOfBooks = arrayBooks.length();

            for(int i=0; i<numberOfBooks; i++){
                JSONObject bookJson = arrayBooks.getJSONObject(i);
                JSONObject volumeInfoJson = bookJson.getJSONObject(VOLUMEINFO);
                int authorNum = volumeInfoJson.getJSONArray(AUTHORS).length();

                String[] authors = new String[authorNum];

                for(int j=0; j<authorNum; j++){
                    authors[j] = volumeInfoJson.getJSONArray(AUTHORS).get(j).toString();

                }

                Book book = new Book(
                        bookJson.getString(ID),
                        volumeInfoJson.getString(TITLE),
                        (volumeInfoJson.isNull(SUBTITLE)?"":volumeInfoJson.getString(SUBTITLE)),
                        authors,
                        volumeInfoJson.getString(PUBLISHER),
                        volumeInfoJson.getString(PUBLISHED_DATE),
                        volumeInfoJson.getString(DESCRIPTION));

                books.add(book);
            }


        } catch(JSONException e){
            e.printStackTrace();
        }


        return books;
    }





}
