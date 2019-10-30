package com.ucse.scrollable.aggregates;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallerClient {

    private static final String URL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin";
    private OkHttpClient client = new OkHttpClient();
    List<Item> items = new ArrayList<>();


    public List<Item> getItems() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(URL)
                .build();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    try (Response response = client.newCall(request).execute()) {
                        String jsonData = response.body().string();
                        JSONObject Jobject = new JSONObject(jsonData);
                        JSONArray Jarray = Jobject.getJSONArray("drinks");

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Item>>(){}.getType();
                        List<Item> contactList = gson.fromJson(Jarray.toString(), type);
                        items.addAll(contactList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {

        }
        return items;
    }
}
