package com.jaydp.samplepayment.payment;

/**
 * A contract on how the View & Presenter interact
 */
public abstract class PaymentContract {

  public interface MvpView {

  }

  public interface Presenter {

    void onPayClicked();
  }
}
