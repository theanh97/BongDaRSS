package com.theanhdev97.tintucbongda.screen.screen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.theanhdev97.tintucbongda.R;

public class FlashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flash);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(FlashActivity.this, HomeActivity.class));
        finish();
      }
    }, 4000);
  }
}
