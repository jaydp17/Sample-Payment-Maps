package com.jaydp.samplepayment;

import android.app.Application;
import com.instamojo.android.Instamojo;

/**
 *
 */
public class MyApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Instamojo.initialize(this);
  }
}
