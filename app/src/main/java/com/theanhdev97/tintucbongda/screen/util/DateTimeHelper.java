package com.theanhdev97.tintucbongda.screen.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DELL on 04/01/2018.
 */

public class DateTimeHelper {
  public static String countTimeBetweenTwoDay(String fromDate) {
    String result = "";
    Date firstDate = convertStringToDate(fromDate);
    Date secondDate = Calendar.getInstance().getTime();

    //milliseconds
    long different = secondDate.getTime() - firstDate.getTime() - 7 * 1000 * 60 * 60;

    long secondsInMilli = 1000;
    long minutesInMilli = secondsInMilli * 60;
    long hoursInMilli = minutesInMilli * 60;
    long daysInMilli = hoursInMilli * 24;

    long elapsedDays = different / daysInMilli;
    different = different % daysInMilli;

    long elapsedHours = different / hoursInMilli;
    different = different % hoursInMilli;

    long elapsedMinutes = different / minutesInMilli;
    different = different % minutesInMilli;

    long elapsedSeconds = different / secondsInMilli;

    if (elapsedDays > 0)
      return elapsedDays + "d";
    if (elapsedHours > 0)
      return elapsedHours + "h";
    if (elapsedMinutes > 0)
      return elapsedMinutes + "m";
    else
      return elapsedSeconds + "s";
  }

  public static Date convertStringToDate(String date) {
//    String date2 = "Thu Jan 04 05:53:47 +0000 2018";
    String format = "dd/MM/yyyy HH:mm:ss";
//    date = "26/06/1997 00:55:32";
//    date = splitStringToValidFormatDate(date);

    SimpleDateFormat spf = new SimpleDateFormat(format);
    Date newDate = null;
    try {
      newDate = spf.parse(date);
    } catch (ParseException e) {
      Log.e("tag123", e.getMessage());
    }
    return newDate;
  }

 /**
   * Giới hạn time của DatePickerDialog bằng ngày hiện tại
   *
   * @param datePickerDialog
   */
  public static void limitDateOfCalendar(DatePickerDialog datePickerDialog) {
    Date currentDay = Calendar.getInstance().getTime();
    Date maxDate = new Date(currentDay.getTime() /*+ (1000 * 60 * 60 * 24)*/);
    datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
  }

  /**
   * Giới hạn time của DatePickerDialog bằng ngày hiện tại - dayCount
   *
   * @param datePickerDialog
   * @param dayCount         số ngày giới hạn lùi lại tính từ thời điểm hiện tại
   */
  public static void limitDateOfCalendar(DatePickerDialog datePickerDialog, int dayCount) {
    Date currentDay = Calendar.getInstance().getTime();
    Date maxDate = new Date(currentDay.getTime() - (1000 * 60 * 60 * 24) * dayCount);
    datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
  }

  public static String getPreviousCurrentDate() {
    Date date = Calendar.getInstance().getTime();
    Date previousDate = new Date(date.getTime() - (1000 * 60 * 60 * 24));
    String result = new SimpleDateFormat("dd/MM/yyyy").format(previousDate);
    return result;
  }

    // Show DatePickerDialog
    // HIDE column Day + Month => Select only Year
  public static void showYearPicker(Context context, final EditText editText, int year, int
      month, int day) {
    DatePickerDialog mDatePickerDialog =
        new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT,
            new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editText.setText(i + "",
                    TextView.BufferType.EDITABLE);
              }
            }, year, month, day);
    try {
      Field[] datePickerDialogFields = mDatePickerDialog.getClass().getDeclaredFields();
      for (Field datePickerDialogField : datePickerDialogFields) {
        if (datePickerDialogField.getName().equals("mDatePicker")) {
          datePickerDialogField.setAccessible(true);
          DatePicker datePicker =
              (DatePicker) datePickerDialogField.get(mDatePickerDialog);
          if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // hide day
            int daySpinnerId =
                Resources.getSystem().getIdentifier("day", "id", "android");
            if (daySpinnerId != 0) {
              View daySpinner = datePicker.findViewById(daySpinnerId);
              if (daySpinner != null) {
                //Ẩn cột date, chỉ còn lại month và year
                daySpinner.setVisibility(View.GONE);
              }
            }
            // hide month
            int monthSpinnerID =
                Resources.getSystem().getIdentifier("month", "id", "android");
            if (monthSpinnerID != 0) {
              View monthSpinner = datePicker.findViewById(monthSpinnerID);
              if (monthSpinner != null) {
                //Ẩn cột date, chỉ còn lại month và year
                monthSpinner.setVisibility(View.GONE);
              }
            }
          }
        }
      }
    } catch (IllegalAccessException e) {
      Log.e("tag123", "IllegalAccessException: ", e);
    }
    mDatePickerDialog.show();
  }

}
