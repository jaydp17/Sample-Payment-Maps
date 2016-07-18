package com.jaydp.samplepayment.payment.dependency.injection;

import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.injectionbase.scope.PerActivity;
import com.jaydp.samplepayment.payment.PaymentActivity;
import dagger.Component;

@PerActivity @Component(modules = PaymentModule.class, dependencies = AppComponent.class)
public interface PaymentComponent {
  void inject(PaymentActivity activity);
}
