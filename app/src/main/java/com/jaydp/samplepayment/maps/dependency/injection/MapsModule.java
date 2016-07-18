package com.jaydp.samplepayment.maps.dependency.injection;

import android.support.annotation.NonNull;
import com.jaydp.samplepayment.injectionbase.scope.PerActivity;
import com.jaydp.samplepayment.maps.MapsActivity;
import com.jaydp.samplepayment.maps.MapsPresenter;
import dagger.Module;
import dagger.Provides;

import static com.jaydp.samplepayment.maps.MapsContract.MvpView;
import static com.jaydp.samplepayment.maps.MapsContract.Presenter;

@Module public class MapsModule {

  private final MapsActivity mActivity;

  public MapsModule(@NonNull MapsActivity activity) {
    mActivity = activity;
  }

  @Provides @PerActivity public MvpView provideView() {
    return mActivity;
  }

  @Provides @PerActivity public Presenter presenter(MvpView view) {
    return new MapsPresenter(view);
  }
}
