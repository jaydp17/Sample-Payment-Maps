package com.jaydp.samplepayment.payment.gateways;

import android.support.annotation.NonNull;
import android.util.Log;
import com.jaydp.samplepayment.BuildConfig;
import com.jaydp.samplepayment.R;
import com.jaydp.samplepayment.payment.PaymentActivity;
import com.razorpay.Checkout;
import org.json.JSONException;
import org.json.JSONObject;

import static com.jaydp.samplepayment.payment.PaymentContract.MvpView;

/**
 * A presenter that manages taking payments through RazorPay
 */
public class RazorPayPresenter implements GateWayContract.Presenter {

  private static final String TAG = "RazorPayPresenter";
  private final MvpView mView;

  public RazorPayPresenter(MvpView view) {
    mView = view;
  }

  @Override
  public void pay(@NonNull String buyerName, @NonNull String buyerEmail, @NonNull String buyerPhone,
      @NonNull String amount, @NonNull String description) {
    final Checkout checkout = new Checkout();
    checkout.setPublicKey(BuildConfig.RAZORPAY_PUBLIC_KEY);
    checkout.setImage(R.mipmap.ic_launcher);

    try {
      final JSONObject details = getPaymentDetails(amount, description);
      details.put("prefill", getUserDetails(buyerName, buyerEmail, buyerPhone));
      checkout.open((PaymentActivity) mView.getContext(), details);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Details of the transaction, like name of the company taking money, amount, & a little bit of
   * description about the transaction
   */
  private JSONObject getPaymentDetails(@NonNull String amount, @NonNull String description) {
    JSONObject obj = new JSONObject();
    try {
      obj.put("description", description);
      obj.put("amount", amount);
      obj.put("name", "Jaydp Corp");
      obj.put("theme", new JSONObject("{color: '#455A64'}"));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * Details of the person who's paying
   */
  private JSONObject getUserDetails(@NonNull String buyerName, @NonNull String buyerEmail,
      @NonNull String buyerPhone) {
    JSONObject details = new JSONObject();
    try {
      details.put("name", buyerName);
      details.put("email", buyerEmail);
      details.put("contact", buyerPhone);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return details;
  }

  /**
   * Callback for Payment Success
   */
  public void onPaymentSuccess(String razorpayPaymentID) {
    mView.showSnackBar("Payment SuccessFul");
  }

  /**
   * Callback for Payment Error
   */
  public void onPaymentError(int code, String response) {
    Log.d(TAG, "onPaymentError(code): " + code);
    Log.d(TAG, "onPaymentError: " + response);
    mView.showSnackBar("Payment Cancelled");
  }
}
