package com.jaydp.samplepayment.payment;

import android.support.annotation.NonNull;
import com.jaydp.samplepayment.base.BaseContract;

/**
 * A contract on how the View & Presenter interact
 */
public abstract class PaymentContract {

  public interface MvpView extends BaseContract.MvpView {

    void showSnackBar(@NonNull String text);
  }

  public interface Presenter {

    void onPayClicked();

    void onPaymentSuccess(String razorpayPaymentID);

    void onPaymentError(int code, String response);

  }
}
