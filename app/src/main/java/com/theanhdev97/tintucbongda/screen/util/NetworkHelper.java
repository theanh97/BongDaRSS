package com.theanhdev97.tintucbongda.screen.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DELL on 01/02/2018.
 */

public class NetworkHelper {
  public static Boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();  }

  public static String downloadContent(String myurl) throws IOException {
    InputStream is = null;
    HttpURLConnection conn = null;
    int length = 500;

    try {
      URL url = new URL(myurl);
      conn = (HttpURLConnection) url.openConnection();
      conn.setReadTimeout(10000);
      conn.setConnectTimeout(15000);
      conn.setRequestMethod("GET");
      conn.setDoInput(true);
      conn.connect();
//            int response = conn.getResponseCode();
      is = conn.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String line = "";
      StringBuilder builder = new StringBuilder();
      while ((line = br.readLine()) != null) {
        builder.append(line);
      }
      return builder.toString();
    } finally {
      if (is != null) {
        is.close();
      }
      conn.disconnect();
    }
  }
}
