package com.jaydp.samplepayment.payment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import com.instamojo.android.helpers.Constants;
import com.jaydp.samplepayment.R;
import com.jaydp.samplepayment.base.BaseActivity;
import com.jaydp.samplepayment.databinding.ActivityMainBinding;
import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.payment.dependency.injection.DaggerPaymentComponent;
import com.jaydp.samplepayment.payment.dependency.injection.PaymentModule;
import javax.inject.Inject;

import static com.jaydp.samplepayment.payment.PaymentContract.MvpView;
import static com.jaydp.samplepayment.payment.PaymentContract.Presenter;

public class PaymentActivity extends BaseActivity implements MvpView {

  @Inject Presenter mPresenter;
  private ActivityMainBinding mBinding;
  private ProgressDialog mProgressDialog;

  public static Intent intent(@NonNull Context context) {
    return new Intent(context, PaymentActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    initUI();
  }

  @Override protected void injectDependencies(AppComponent appComponent) {
    DaggerPaymentComponent.builder()
        .appComponent(appComponent)
        .paymentModule(new PaymentModule(this))
        .build()
        .inject(this);
  }

  /**
   * Initializes all the UI components
   */
  private void initUI() {
    mBinding.razorPay.setOnClickListener(v -> mPresenter.onRazorPayClicked());
    mBinding.instaPay.setOnClickListener(v -> mPresenter.onInstaPayClicked());
  }

  /**
   * RazorPay requires the name of this method to be <tt>onPaymentSuccess</tt>
   */
  public void onPaymentSuccess(String razorpayPaymentID) {
    mPresenter.onRazorPaySuccess(razorpayPaymentID);
  }

  /**
   * RazorPay requires the name of this method to be <tt>onPaymentError</tt>
   */
  public void onPaymentError(int code, String response) {
    mPresenter.onRazorPayError(code, response);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constants.REQUEST_CODE && data != null) {
      mPresenter.onInstaMojoResult(data);
    }
  }

  /**
   * Shows a snackbar with the given text
   *
   * @param text Text to show inside the snackbar
   */
  @Override public void showSnackBar(@NonNull String text) {
    Snackbar.make(mBinding.getRoot(), text, Snackbar.LENGTH_LONG).show();
  }

  /**
   * Shows a progress dialog with the given string resource
   */
  @Override public void showProgressDialog(@StringRes int resId) {
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage(getString(resId));
    mProgressDialog.show();
  }

  /**
   * Hide the progress bar if it's currently visible
   */
  @Override public void hideProgressDialog() {
    if (mProgressDialog != null) {
      runOnUiThread(() -> mProgressDialog.hide());
    }
  }

  @Override protected void onStop() {
    hideProgressDialog();
    super.onStop();
  }
}
