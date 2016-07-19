package com.jaydp.samplepayment.payment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import com.jaydp.samplepayment.base.BaseContract;

/**
 * A contract on how the View & Presenter interact
 */
public abstract class PaymentContract {

  public interface MvpView extends BaseContract.MvpView {

    void showSnackBar(@NonNull String text);

    void showProgressDialog(@StringRes int resId);

    void hideProgressDialog();
  }

  public interface Presenter {

    void onRazorPayClicked();

    void onRazorPaySuccess(String razorpayPaymentID);

    void onRazorPayError(int code, String response);

    void onInstaPayClicked();

    void onInstaMojoResult(@NonNull Intent data);
  }
}
