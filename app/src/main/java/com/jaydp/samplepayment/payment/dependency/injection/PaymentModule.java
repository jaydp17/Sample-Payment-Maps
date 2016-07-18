package com.jaydp.samplepayment.payment.dependency.injection;

import android.support.annotation.NonNull;
import com.jaydp.samplepayment.injectionbase.scope.PerActivity;
import com.jaydp.samplepayment.payment.PaymentActivity;
import com.jaydp.samplepayment.payment.PaymentPresenter;
import dagger.Module;
import dagger.Provides;

import static com.jaydp.samplepayment.payment.PaymentContract.MvpView;
import static com.jaydp.samplepayment.payment.PaymentContract.Presenter;

@Module public class PaymentModule {

  private final PaymentActivity mActivity;

  public PaymentModule(@NonNull PaymentActivity activity) {
    mActivity = activity;
  }

  @Provides @PerActivity public MvpView provideView() {
    return mActivity;
  }

  @Provides @PerActivity public Presenter presenter(MvpView view) {
    return new PaymentPresenter(view);
  }
}