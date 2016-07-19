package com.jaydp.samplepayment.payment.gateways;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import com.instamojo.android.Instamojo;
import com.instamojo.android.activities.PaymentDetailsActivity;
import com.instamojo.android.helpers.Constants;
import com.instamojo.android.models.Order;
import com.instamojo.android.network.Request;
import com.jaydp.samplepayment.BuildConfig;
import com.jaydp.samplepayment.R;
import java.util.UUID;

import static com.jaydp.samplepayment.payment.PaymentContract.MvpView;

/**
 * A presenter that manages taking payments through InstaMojo
 */
public class InstaMojoPresenter implements GateWayContract.Presenter {

  private static final String TAG = "InstaMojoPresenter";
  private final MvpView mView;

  public InstaMojoPresenter(MvpView view) {
    mView = view;
    Instamojo.setLogLevel(Log.DEBUG);
    Instamojo.setBaseUrl("https://test.instamojo.com/");
  }

  @Override
  public void pay(@NonNull String buyerName, @NonNull String buyerEmail, @NonNull String buyerPhone,
      @NonNull String amount, @NonNull String description) {
    final String transactionID = UUID.randomUUID().toString();
    Order order =
        new Order(BuildConfig.INSTAMOJO_ACCESS_TOKEN, transactionID, buyerName, buyerEmail,
            buyerPhone, amount, description);

    if (isOrderValid(order)) {
      createOrderOnInstaMojo(order);
    }
  }

  private void createOrderOnInstaMojo(@NonNull Order order) {
    mView.showProgressDialog(R.string.creating_order);
    Request request = new Request(order, (order1, error) -> {
      mView.hideProgressDialog();
      if (error != null) {
        error.printStackTrace();
        mView.showSnackBar("Payment through Instamojo Failed");
        return;
      }
      askPaymentDetails(order);
    });
    request.execute();
  }

  /**
   * Start an activity to ask for Card/Net Banking details
   *
   * @param order The InstaMojo Order Object
   */
  private void askPaymentDetails(@NonNull Order order) {
    //Using Pre created UI
    Intent intent = new Intent(mView.getContext(), PaymentDetailsActivity.class);
    intent.putExtra(Constants.ORDER, order);
    mView.startActivityForResult(intent, Constants.REQUEST_CODE);
  }

  /**
   * When the payment as finished, this method gets called
   *
   * @param data Contains information about the transaction
   */
  public void onPaymentResult(@NonNull Intent data) {
    String orderID = data.getStringExtra(Constants.ORDER_ID);
    String transactionID = data.getStringExtra(Constants.TRANSACTION_ID);
    String paymentID = data.getStringExtra(Constants.PAYMENT_ID);

    // Check transactionID, orderID, and orderID for null before using them to check the Payment status.
    if (orderID != null && transactionID != null && paymentID != null) {
      mView.showSnackBar("Payment SuccessFul");
    } else {
      mView.showSnackBar("Payment Cancelled");
    }
  }

  /**
   * Checks whether the order is valid
   */
  private boolean isOrderValid(@NonNull Order order) {
    if (!order.isValid()) {

      if (!order.isValidName()) {
        Log.e(TAG, "isInstaOrderValid: Buyer name is invalid");
      }

      if (!order.isValidEmail()) {
        Log.e(TAG, "isInstaOrderValid: Buyer email is invalid");
      }

      if (!order.isValidPhone()) {
        Log.e(TAG, "isInstaOrderValid: Buyer phone is invalid");
      }

      if (!order.isValidAmount()) {
        Log.e(TAG, "isInstaOrderValid: Amount is invalid");
      }

      if (!order.isValidDescription()) {
        Log.e(TAG, "isInstaOrderValid: description is invalid");
      }

      if (!order.isValidTransactionID()) {
        Log.e(TAG, "isInstaOrderValid: Transaction ID is invalid");
      }

      if (!order.isValidRedirectURL()) {
        Log.e(TAG, "isInstaOrderValid: Redirection URL is invalid");
      }

      return false;
    }
    return true;
  }
}
