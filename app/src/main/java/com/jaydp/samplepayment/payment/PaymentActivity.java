package com.jaydp.samplepayment.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
    mBinding.button.setOnClickListener(v -> mPresenter.onPayClicked());
  }

  /**
   * RazorPay requires the name of this method to be <tt>onPaymentSuccess</tt>
   */
  public void onPaymentSuccess(String razorpayPaymentID) {
    mPresenter.onPaymentSuccess(razorpayPaymentID);
  }

  /**
   * RazorPay requires the name of this method to be <tt>onPaymentError</tt>
   */
  public void onPaymentError(int code, String response) {
    mPresenter.onPaymentError(code, response);
  }

  /**
   * Shows a snackbar with the given text
   *
   * @param text Text to show inside the snackbar
   */
  @Override public void showSnackBar(@NonNull String text) {
    Snackbar.make(mBinding.getRoot(), text, Snackbar.LENGTH_LONG).show();
  }
}
