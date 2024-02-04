package com.example.umoove;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpService {

    private OkHttpClient client;
    private Gson gson;

    private final String basePath = "https://umoove.co/api";

    public HttpService() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public List<Activity> getActivities() throws IOException {
        Response response = getRequest(basePath + "/activities");

        return new ArrayList<>();
    }

    private Response getRequest(String requestString) {
        Request request = new Request.Builder().url(requestString).build();
        Response result;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                }
            }
        });
        return null;
    }
}
