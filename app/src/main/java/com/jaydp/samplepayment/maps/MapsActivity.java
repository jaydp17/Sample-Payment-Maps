package com.jaydp.samplepayment.maps;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaydp.samplepayment.R;
import com.jaydp.samplepayment.base.BaseActivity;
import com.jaydp.samplepayment.databinding.ActivityMapsBinding;
import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.maps.dependency.injection.DaggerMapsComponent;
import com.jaydp.samplepayment.maps.dependency.injection.MapsModule;
import javax.inject.Inject;

import static com.jaydp.samplepayment.maps.MapsContract.MvpView;
import static com.jaydp.samplepayment.maps.MapsContract.Presenter;

public class MapsActivity extends BaseActivity implements MvpView {

  @Inject Presenter mPresenter;
  private GoogleMap mMap;
  private ActivityMapsBinding mBinding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_maps);
    initUI();
  }

  private void initUI() {
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment =
        (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(mPresenter);
    mBinding.bookRide.setOnClickListener(v -> mPresenter.onBookClicked());
  }

  @Override protected void injectDependencies(AppComponent appComponent) {
    DaggerMapsComponent.builder()
        .appComponent(appComponent)
        .mapsModule(new MapsModule(this))
        .build()
        .inject(this);
  }

  @Override public void setGoogleMap(GoogleMap googleMap) {
    mMap = googleMap;
  }

  @Override public void moveCamera(CameraUpdate update) {
    mMap.moveCamera(update);
  }

  @Override public Marker addMarkerOnMap(MarkerOptions markerOptions) {
    return mMap.addMarker(markerOptions);
  }
}
