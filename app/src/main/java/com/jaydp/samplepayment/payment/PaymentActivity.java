package com.jaydp.samplepayment.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.jaydp.samplepayment.R;
import com.jaydp.samplepayment.base.BaseActivity;
import com.jaydp.samplepayment.databinding.ActivityMainBinding;
import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.payment.dependency.injection.DaggerPaymentComponent;
import com.jaydp.samplepayment.payment.dependency.injection.PaymentModule;
import javax.inject.Inject;

public class PaymentActivity extends BaseActivity implements PaymentContract.MvpView {

  @Inject PaymentContract.Presenter mPresenter;
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

  private void initUI() {
    mBinding.button.setOnClickListener(v -> mPresenter.onPayClicked());
  }
}
