package com.jaydp.samplepayment.maps;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaydp.samplepayment.base.BaseContract;

/**
 * A contract on how the View & Presenter will interact
 */
public abstract class MapsContract {

  public interface MvpView extends BaseContract.MvpView {

    void setGoogleMap(GoogleMap googleMap);

    void moveCamera(CameraUpdate update);

    Marker addMarkerOnMap(MarkerOptions markerOptions);
  }

  public interface Presenter extends OnMapReadyCallback {

  }
}
