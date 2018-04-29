package com.theanhdev97.tintucbongda.screen.model;

import java.util.ArrayList;

/**
 * Created by DELL on 29/04/2018.
 */

public interface ResponseListener {
  void onSuccess(ArrayList<News> news);

  void onFailure(String error);
}
