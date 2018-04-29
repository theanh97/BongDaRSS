package com.theanhdev97.tintucbongda.screen.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theanhdev97.tintucbongda.R;
import com.theanhdev97.tintucbongda.screen.adapter.NewsAdapter;
import com.theanhdev97.tintucbongda.screen.adapter.NewsClickListener;
import com.theanhdev97.tintucbongda.screen.asynctask.GetNewsAsynctask;
import com.theanhdev97.tintucbongda.screen.model.News;
import com.theanhdev97.tintucbongda.screen.model.ResponseListener;
import com.theanhdev97.tintucbongda.screen.screen.NewsActivity;
import com.theanhdev97.tintucbongda.screen.util.Constants;
import com.theanhdev97.tintucbongda.screen.util.EndlessRecyclerViewScrollListener;
import com.theanhdev97.tintucbongda.screen.util.NetworkHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DELL on 29/04/2018.
 */

public class NewsFragment extends Fragment implements ResponseListener, NewsClickListener, SwipeRefreshLayout.OnRefreshListener {
  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;
  @BindView(R.id.swipe_refresh_layout)
  SwipeRefreshLayout mSwipeRefreshLayout;

  private Context mContext;
  private String mUrl;
  private int mCurrentPage = 0;
  private boolean mIsFirstLaunch;
  private EndlessRecyclerViewScrollListener mEndlessRecyclerViewScrollListener;
  private NewsAdapter mNewsAdapter;
  private ArrayList<News> mNews;
  private LinearLayoutManager mLayoutManager;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    mUrl = getArguments().getString(Constants.KEY_NEWS_URL);
    mContext = getContext();
    return inflater.inflate(R.layout.news_fragment, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    prepareUI();
    getNewsByPage(mCurrentPage);
  }

  private void prepareUI() {
    mNews = new ArrayList<News>();
    mLayoutManager = new LinearLayoutManager(mContext);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mSwipeRefreshLayout.setRefreshing(true);
    mIsFirstLaunch = true;

    mSwipeRefreshLayout.setOnRefreshListener(this);
  }

  public void getNewsByPage(int page) {
    if (NetworkHelper.isNetworkAvailable(mContext))
      new GetNewsAsynctask(this).execute(mUrl);
    else {
      loadDataToView(null);
    }
  }

  @Override
  public void onSuccess(ArrayList<News> news) {
    loadDataToView(news);
  }

  private void loadDataToView(ArrayList<News> news) {
    // no data
    if (news == null || news.size() == 0) {
      showViewError("Network is error");
    }
    // have
    else {
//      mListProductSameTypes.clear();
      mNews.addAll(news);
      mCurrentPage++;
    }

    if (mIsFirstLaunch) {
      mNewsAdapter = new NewsAdapter(mContext, mNews);
      mNewsAdapter.setOnClickListener(this);
      mRecyclerView.setAdapter(mNewsAdapter);
      mEndlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
          if (mNews.size() != 0)
            getNewsByPage(mCurrentPage);
        }
      };
      mRecyclerView.addOnScrollListener(mEndlessRecyclerViewScrollListener);
      mIsFirstLaunch = false;
    }

    mNewsAdapter.notifyDataSetChanged();

    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void onFailure(String error) {
    loadDataToView(null);
    showViewError(error);
  }

  private void showViewError(String error) {
    Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void onClick(int position) {
    News news = mNews.get(position);
    Intent i = new Intent(mContext, NewsActivity.class);
    i.putExtra(Constants.KEY_NEWS_URL, news.getLink());
    startActivity(i);
  }

  @Override
  public void onRefresh() {
    mCurrentPage = 0;
    mSwipeRefreshLayout.setRefreshing(true);
    getNewsByPage(mCurrentPage);
  }
}
