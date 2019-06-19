package com.r.books;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiUtil {
    private ApiUtil(){

    }

    public static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    //create a URL
    public static URL buildUrl(String title) {
        String fullUrl = BASE_URL + "?q=" + title;
        URL url = null;
        try{
            url = new URL(fullUrl);
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





}
