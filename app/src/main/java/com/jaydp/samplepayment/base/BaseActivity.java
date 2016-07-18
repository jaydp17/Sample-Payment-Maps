package com.jaydp.samplepayment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.jaydp.samplepayment.MyApp;
import com.jaydp.samplepayment.injectionbase.components.AppComponent;

/**
 * The base for all activities in this project
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.MvpView {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectDependencies(MyApp.getAppComponent());
  }

  protected abstract void injectDependencies(AppComponent appComponent);

  @Override public Context getContext() {
    return this;
  }
}
