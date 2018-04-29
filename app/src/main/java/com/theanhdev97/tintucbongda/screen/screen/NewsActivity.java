package com.theanhdev97.tintucbongda.screen.screen;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.theanhdev97.tintucbongda.R;
import com.theanhdev97.tintucbongda.screen.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
  @BindView(R.id.webview)
  WebView mWebView;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.swipe_refresh_layout)
  SwipeRefreshLayout mSwipeRefreshLayout;

  private String mLink;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);
    mLink = getIntent().getStringExtra(Constants.KEY_NEWS_URL);
    prepairUI();
    configWebView(mLink);
  }

  private void prepairUI() {
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });

    SpannableString spannablecontent = new SpannableString("News");
    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#00A6E7")),
        0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#962071")),
        1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#C5F0A4")),
        2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    getSupportActionBar().setTitle(spannablecontent);


    mSwipeRefreshLayout.setOnRefreshListener(this);
  }

  private void configWebView(String url) {
    mWebView.setWebViewClient(new WebViewClient() {
      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mSwipeRefreshLayout.setRefreshing(true);
      }

      @Override
      public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        mSwipeRefreshLayout.setRefreshing(false);
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mSwipeRefreshLayout.setRefreshing(false);
      }
    });
    mWebView.getSettings().setLoadsImagesAutomatically(true);
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    mWebView.loadUrl(url);

  }

  @Override
  public void onRefresh() {
//    mWebView.reload();
    mWebView.loadUrl(mLink);
  }
}
