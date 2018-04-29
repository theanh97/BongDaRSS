package com.theanhdev97.tintucbongda.screen.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by DELL on 29/04/2018.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
  ArrayList<Fragment> mListFragments;
  ArrayList<String> mListTitle;

  public MyFragmentPagerAdapter(FragmentManager fm) {
    super(fm);
    mListFragments = new ArrayList<Fragment>();
    mListTitle = new ArrayList<String>();
  }

  public void addFragment(Fragment fragment, String title) {
    mListFragments.add(fragment);
    mListTitle.add(title);
  }

  @Override
  public Fragment getItem(int position) {
    return mListFragments.get(position);
  }

  @Override
  public int getCount() {
    return mListTitle.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mListTitle.get(position);
  }
}
