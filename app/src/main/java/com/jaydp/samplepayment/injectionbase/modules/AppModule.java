package com.jaydp.samplepayment.injectionbase.modules;

import com.jaydp.samplepayment.MyApp;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {

  private final MyApp mApp;

  public AppModule(MyApp app) {
    mApp = app;
  }

  @Provides @Singleton public MyApp application() {
    return this.mApp;
  }
}
