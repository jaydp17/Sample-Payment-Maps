package com.jaydp.samplepayment.payment;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.jaydp.samplepayment.payment.gateways.GateWayContract;
import com.jaydp.samplepayment.payment.gateways.InstaMojoPresenter;
import com.jaydp.samplepayment.payment.gateways.RazorPayPresenter;

import static com.jaydp.samplepayment.payment.PaymentContract.Presenter;

/**
 *
 */
public class PaymentPresenter implements Presenter {

  @NonNull private final RazorPayPresenter mRazorPayPresenter;
  @NonNull private final InstaMojoPresenter mInstaPresenter;

  public PaymentPresenter(@NonNull RazorPayPresenter razorPayPresenter,
      @NonNull InstaMojoPresenter instaPresenter) {
    mRazorPayPresenter = razorPayPresenter;
    mInstaPresenter = instaPresenter;
  }

  /**
   * Pay using RazorPay
   */
  @Override public void onRazorPayClicked() {
    internalPay(mRazorPayPresenter);
  }

  /**
   * Pay Using InstaMojo
   */
  @Override public void onInstaPayClicked() {
    internalPay(mInstaPresenter);
  }

  /**
   * Adds the details of the Buyer to a Gateway Presenter
   * This method doesn't care which Payment Gateway is used, its only job is to use the GateWay
   * Presenter Contract and passthe Buyer details
   */
  private void internalPay(@NonNull GateWayContract.Presenter gatewayPresenter) {
    gatewayPresenter.pay("Jaydeep", "jaydp17@gmail.com", "9033819605", "100", "Testing payment");
  }

  /**
   * Callback for RazorPay Payment Success
   */
  @Override public void onRazorPaySuccess(String razorpayPaymentID) {
    mRazorPayPresenter.onPaymentSuccess(razorpayPaymentID);
  }

  /**
   * Callback for RazorPay Payment Error
   */
  @Override public void onRazorPayError(int code, String response) {
    mRazorPayPresenter.onPaymentError(code, response);
  }

  @Override public void onInstaMojoResult(@NonNull Intent data) {
    mInstaPresenter.onPaymentResult(data);
  }
}
