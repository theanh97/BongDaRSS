package com.theanhdev97.tintucbongda.screen.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theanhdev97.tintucbongda.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DELL on 29/04/2018.
 */

public class AboutDialogFragment extends DialogFragment {
  @BindView(R.id.tv_description)
  TextView mDescription;
  @BindView(R.id.tv_subject)
  TextView mSubject;
  @BindView(R.id.tv_studens_execution)
  TextView mStudentExecution;
  @BindView(R.id.tv_version)
  TextView mVersion;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getDialog().setTitle("About");
    return inflater.inflate(R.layout.dialog_fragment_about, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    prepareUI();
  }

  private void prepareUI() {
    // Subject
    SpannableString spannablecontent = new SpannableString("Đề tài thực hiện : Ứng dụng đọc báo thông qua RSS");
    spannablecontent.setSpan(new StyleSpan(Typeface.BOLD),
        0, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mSubject.setText(spannablecontent);

    // Student Execution
    SpannableString spannablecontent1 = new SpannableString("Sinh viên thực hiện :" +
        "\n\t + Phạm Thế Anh - 15110009 \n\t + Lê Quang Sơn - 15110010");
    spannablecontent1.setSpan(new StyleSpan(Typeface.BOLD),
        0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mStudentExecution.setText(spannablecontent1);

    // Description
    SpannableString spannablecontent2 = new SpannableString("Miêu tả : Ứng dụng đọc tin tức về bóng đá có nguồn từ page Bongda.com.vn");
    spannablecontent2.setSpan(new StyleSpan(Typeface.BOLD),
        0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mDescription.setText(spannablecontent2);

    // Version
    SpannableString spannablecontent3 = new SpannableString("Version : 1.0");
    spannablecontent3.setSpan(new StyleSpan(Typeface.BOLD),
        0, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mVersion.setText(spannablecontent3);
  }
}
