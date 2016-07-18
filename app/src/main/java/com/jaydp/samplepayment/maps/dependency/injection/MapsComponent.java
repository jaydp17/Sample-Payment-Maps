package com.jaydp.samplepayment.maps.dependency.injection;

import com.jaydp.samplepayment.injectionbase.components.AppComponent;
import com.jaydp.samplepayment.injectionbase.scope.PerActivity;
import com.jaydp.samplepayment.maps.MapsActivity;
import dagger.Component;

@PerActivity @Component(modules = MapsModule.class, dependencies = AppComponent.class)
public interface MapsComponent {
  void inject(MapsActivity activity);
}
