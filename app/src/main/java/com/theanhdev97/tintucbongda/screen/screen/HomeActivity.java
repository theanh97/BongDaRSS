package com.theanhdev97.tintucbongda.screen.screen;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.theanhdev97.tintucbongda.R;
import com.theanhdev97.tintucbongda.screen.adapter.MyFragmentPagerAdapter;
import com.theanhdev97.tintucbongda.screen.fragment.AboutDialogFragment;
import com.theanhdev97.tintucbongda.screen.fragment.NewsFragment;
import com.theanhdev97.tintucbongda.screen.util.Constants;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.view_pager)
  ViewPager mViewPager;
  @BindView(R.id.tabs)
  TabLayout mTabLayout;
  @BindView(R.id.text_view_toolbar_title)
  TextView mTextViewToolbarTitle;

  List<String> mListNewsTitles;
  List<String> mListNewsUrls;
  MyFragmentPagerAdapter mPagerAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);
    initData();
    prepareUI();
  }

  public void initData() {
    mListNewsTitles = getNewsTitles();
    mListNewsUrls = getNewsUrls();
  }

  public List<String> getNewsTitles() {
    String[] array = getResources().getStringArray(R.array.news_title);
    return Arrays.asList(array);
  }

  public List<String> getNewsUrls() {
    String[] array = getResources().getStringArray(R.array.news_url);
    return Arrays.asList(array);
  }

  void prepareUI() {
    setSupportActionBar(mToolbar);

    SpannableString spannablecontent = new SpannableString("Bongda.com.vn");
    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#00A6E7")),
        0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#962071")),
        1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#C5F0A4")),
        2, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#4ECCA3")),
        3, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#F8B595")),
        4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    spannablecontent.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4D4D")),
        5, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    spannablecontent.setSpan(new ForegroundColorSpan(Color.GRAY),
        6, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    mTextViewToolbarTitle.setText(spannablecontent);

    // init tab + viewpager
    mPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    for (int i = 0; i < mListNewsUrls.size(); i++) {
      NewsFragment fragment = new NewsFragment();
      Bundle bundle = new Bundle();
      bundle.putString(Constants.KEY_NEWS_URL, mListNewsUrls.get(i));
      fragment.setArguments(bundle);

      mPagerAdapter.addFragment(fragment, mListNewsTitles.get(i));
    }
    mViewPager.setAdapter(mPagerAdapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId() == R.id.action_about){
      showAboutDialog();
    }
    return true;
  }

  public void showAboutDialog(){
//    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//        this);
//
//    // set title
//    alertDialogBuilder.setTitle("Your Title");
//
//    // set dialog message
//    String content = getResources().getString(R.string.about);
//    alertDialogBuilder
//        .setTitle("About")
//        .setMessage(content)
//        .setCancelable(false);
//    // create alert dialog
//    AlertDialog alertDialog = alertDialogBuilder.create();
//
//    // show it
//    alertDialog.show();
    AboutDialogFragment dialogFragment = new AboutDialogFragment();
    dialogFragment.show(getSupportFragmentManager(), "dialog");
  }
}
