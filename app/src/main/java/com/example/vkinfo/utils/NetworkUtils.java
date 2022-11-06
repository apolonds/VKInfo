package com.example.vkinfo.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {

    public static final String VK_API_BASE_URL = "https://api.vk.com/";
    public static final String VK_USERS_GET = "method/users.get";
    public static final String PARAM_USER_ID = "user_ids";
    public static final String PARAM_VERSION = "v";

    public static URL generateURL(String userId){
        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(PARAM_USER_ID, userId)
                .appendQueryParameter(PARAM_VERSION, "5.8")
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
