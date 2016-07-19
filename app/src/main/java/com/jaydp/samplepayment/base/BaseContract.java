package com.jaydp.samplepayment.base;

import android.content.Context;
import android.content.Intent;

/**
 * A few methods that each View should have
 */
public interface BaseContract {

  /**
   * Each MvpView should extend this Interface
   */
  interface MvpView {

    Context getContext();

    Intent getIntent();

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);
  }
}
