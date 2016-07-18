package com.jaydp.samplepayment.payment;

import android.support.annotation.NonNull;
import android.util.Log;

import static com.jaydp.samplepayment.payment.PaymentContract.MvpView;
import static com.jaydp.samplepayment.payment.PaymentContract.Presenter;

/**
 *
 */
public class PaymentPresenter implements Presenter {

  private static final String TAG = "PaymentPresenter";
  @NonNull private final MvpView mView;

  public PaymentPresenter(@NonNull MvpView view) {
    mView = view;
  }

  @Override public void onPayClicked() {
    // Do what ever complex logic you want
    Log.d(TAG, "onPayClicked");
  }
}
