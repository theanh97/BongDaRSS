package com.theanhdev97.tintucbongda.screen.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.theanhdev97.tintucbongda.R;

/**
 * Created by DELL on 17/01/2018.
 */

public class ImageHelper {
  public static void loadImage(Context context, ImageView imageView, String url, int
      placeHolderID) {
    RequestOptions options = new RequestOptions()
        .centerCrop()
        .placeholder(placeHolderID)
        .priority(Priority.HIGH);

    Glide.with(context)
        .load(url)
        .apply(options)
        .into(imageView);
  }
}
