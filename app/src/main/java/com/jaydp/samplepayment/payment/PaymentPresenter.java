package com.jaydp.samplepayment.payment;

import android.support.annotation.NonNull;
import android.util.Log;
import com.jaydp.samplepayment.BuildConfig;
import com.jaydp.samplepayment.R;
import com.razorpay.Checkout;
import org.json.JSONException;
import org.json.JSONObject;

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

  /**
   * Listener for Pay Button
   */
  @Override public void onPayClicked() {
    final Checkout co = new Checkout();
    co.setPublicKey(BuildConfig.RAZORPAY_PUBLIC_KEY);
    co.setImage(R.mipmap.ic_launcher);

    try {
      final JSONObject details = getPaymentDetails();
      details.put("prefill", getUserDetails());
      co.open((PaymentActivity) mView.getContext(), details);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Details of the transaction, like name of the company taking money, amount, & a little bit of
   * description about the transaction
   */
  private JSONObject getPaymentDetails() {
    JSONObject obj = new JSONObject();
    try {
      obj.put("description", "Testing Payment");
      obj.put("amount", "100");
      obj.put("name", "Jaydp Corp");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * Details of the person who's paying
   */
  private JSONObject getUserDetails() {
    JSONObject details = new JSONObject();
    try {
      details.put("email", "jaydp17@gmail.com");
      details.put("contact", "9033819605");
      details.put("name", "Jaydeep Solanki");
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return details;
  }

  /**
   * Callback for Payment Success
   */
  @Override public void onPaymentSuccess(String razorpayPaymentID) {
    Log.d(TAG, "onPaymentSuccess: " + razorpayPaymentID);
    mView.showSnackBar("Payment SuccessFul");
  }

  /**
   * Callback for Payment Error
   */
  @Override public void onPaymentError(int code, String response) {
    Log.d(TAG, "onPaymentError(code): " + code);
    Log.d(TAG, "onPaymentError: " + response);
    mView.showSnackBar("Payment Cancelled");
  }
}
