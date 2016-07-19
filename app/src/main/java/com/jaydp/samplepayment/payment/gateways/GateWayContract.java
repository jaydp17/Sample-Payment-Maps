package com.jaydp.samplepayment.payment.gateways;

import android.support.annotation.NonNull;

/**
 * A common interface for all the payment Gateway Presenters
 */
public abstract class GateWayContract {

  public interface Presenter {
    void pay(@NonNull String buyerName, @NonNull String buyerEmail, @NonNull String buyerPhone,
        @NonNull String amount, @NonNull String description);
  }
}
