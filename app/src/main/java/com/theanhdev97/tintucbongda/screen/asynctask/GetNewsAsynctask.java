package com.theanhdev97.tintucbongda.screen.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.theanhdev97.tintucbongda.screen.model.News;
import com.theanhdev97.tintucbongda.screen.model.ResponseListener;
import com.theanhdev97.tintucbongda.screen.util.NetworkHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DELL on 29/04/2018.
 */

public class GetNewsAsynctask extends AsyncTask<String, Void, Object> {
  ResponseListener mResponseListener;

  public GetNewsAsynctask(ResponseListener listener) {
    this.mResponseListener = listener;
  }

  @Override
  protected Object doInBackground(String... strings) {
    ArrayList<News> arrayList = new ArrayList<News>();
    try {
      // convert URL -> InputStream
      URL url = new URL(strings[0]);
      URLConnection ucon = url.openConnection();
      InputStream inputStream = ucon.getInputStream();

      arrayList = readXMl(inputStream);
      return arrayList;
    } catch (MalformedURLException e) {
      return e.getMessage();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(Object result) {
    super.onPostExecute(result);
    if (result instanceof String) {
      mResponseListener.onFailure((String) result);
    }
    // news arraylist
    else {
      mResponseListener.onSuccess((ArrayList<News>) result);
    }
  }

  private ArrayList<News> readXMl(InputStream is) {
    ArrayList<News> arrayList = new ArrayList<News>();
    XmlPullParserFactory xmlFactoryObject = null;

    News news = null;
    String content = null;
    String tagName = null;
    boolean flag = false;
    try {
      xmlFactoryObject = XmlPullParserFactory.newInstance();
      XmlPullParser myparser = xmlFactoryObject.newPullParser();
      myparser.setInput(is, null);
      int event = myparser.getEventType();
      while (event != XmlPullParser.END_DOCUMENT) {
        switch (event) {
          case XmlPullParser.START_TAG:
            tagName = myparser.getName();
            if (tagName.equals("item")) {
              news = new News();
              flag = true;
            }
            break;
          case XmlPullParser.TEXT:
            content = myparser.getText();
            break;
          case XmlPullParser.END_TAG:
            tagName = myparser.getName();
            if (tagName.equals("title") && flag) {
              news.setTitle(content.trim());
              Log.d("tag123", "Title : " + content);
            } else if (tagName.equals("link") && flag) {
              news.setLink(content.trim());
              Log.d("tag123", "Link : " + content);
            } else if (tagName.equals("description") && flag) {
              news.setDescription(content.trim());
              Log.d("tag123", "Description : " + content);
            } else if (tagName.equals("pubDate") && flag) {
//              String time = content.split(" ")[0];
//              SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//              Date convertedDate = new Date();
//              convertedDate = dateFormat.parse(time);
//              news.setPubDate(convertedDate.toString().trim());
              news.setPubDate(content);
              Log.d("tag123", "PubDate : " + content);
            } else if (tagName.equals("image") && flag) {
              news.setImage(content.trim());
              Log.d("tag123", "Image : " + content);
              arrayList.add(news);
            }
            break;
        }
        event = myparser.next();
      }
    } catch (XmlPullParserException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } /*catch (ParseException e) {
      e.printStackTrace();
    }*/
    return arrayList;
  }
}
