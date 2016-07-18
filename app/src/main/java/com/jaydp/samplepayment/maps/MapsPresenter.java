package com.jaydp.samplepayment.maps;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaydp.samplepayment.R;
import java.util.ArrayList;

import static com.jaydp.samplepayment.maps.MapsContract.MvpView;
import static com.jaydp.samplepayment.maps.MapsContract.Presenter;

/**
 *
 */
public class MapsPresenter implements Presenter {

  @NonNull private final MvpView mView;
  private ArrayList<LatLng> mBikerPoints;
  private Marker mBiker;
  private int mBikerPointIndex = 0;

  public MapsPresenter(@NonNull MvpView view) {
    mView = view;
    initBikerPoints();
  }

  /**
   * Called when Google Maps is ready
   */
  @Override public void onMapReady(GoogleMap googleMap) {
    mView.setGoogleMap(googleMap);

    // Set the current location to Amrita Clg
    LatLng amritaClg = new LatLng(12.89466547, 77.6754427);
    mView.moveCamera(CameraUpdateFactory.newLatLngZoom(amritaClg, 15));

    // add a biker on the map
    mBiker = mView.addMarkerOnMap(getMarkerOptions());
    incrementBikerPosition();
  }

  /**
   * Increments the biker position & schedules a new update in 2seconds
   */
  private void incrementBikerPosition() {
    mBiker.setPosition(mBikerPoints.get(mBikerPointIndex++ % mBikerPoints.size()));
    new Handler().postDelayed(this::incrementBikerPosition, 2000);
  }

  /**
   * Marker Options for the Biker
   */
  private MarkerOptions getMarkerOptions() {
    return new MarkerOptions().position(mBikerPoints.get(0))
        .title("Biker")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_motercycle));
  }

  /**
   * Marker Points for the biker
   */
  private void initBikerPoints() {
    mBikerPoints = new ArrayList<>();
    mBikerPoints.add(new LatLng(12.89886966, 77.67546415));
    mBikerPoints.add(new LatLng(12.89822125, 77.67546415));
    mBikerPoints.add(new LatLng(12.89753102, 77.6754427));
    mBikerPoints.add(new LatLng(12.89702902, 77.67522812));
    mBikerPoints.add(new LatLng(12.89631787, 77.67501354));
    mBikerPoints.add(new LatLng(12.89564854, 77.67477751));
    mBikerPoints.add(new LatLng(12.89525113, 77.67458439));
    mBikerPoints.add(new LatLng(12.89477005, 77.67443419));
    mBikerPoints.add(new LatLng(12.89424713, 77.67424107));
  }
}
