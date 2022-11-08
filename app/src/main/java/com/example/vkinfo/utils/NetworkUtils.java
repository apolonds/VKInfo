package com.example.vkinfo.utils;

import android.net.Uri;

import com.example.vkinfo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static final String VK_API_BASE_URL = "https://api.vk.com/";
    public static final String VK_USERS_GET = "method/users.get";
    public static final String PARAM_USER_ID = "user_ids";
    public static final String ACCESS_TOKEN = "access_token";

    public static final String PARAM_VERSION = "v";

    public static URL generateURL(String userIds){
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userIds)
                .appendQueryParameter(PARAM_VERSION, "5.131")
                .appendQueryParameter(ACCESS_TOKEN, ACCESS_TOKEN)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseromURL(URL url){
        HttpURLConnection urlConnection = null;
        try {

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream in = null;
            try {
                in = urlConnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scanner scanner = new Scanner(in); // разбивает строку на подстроки по пробелам.
            scanner.useDelimiter("\\A"); // получает целую строку с начало строки.
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }

}
