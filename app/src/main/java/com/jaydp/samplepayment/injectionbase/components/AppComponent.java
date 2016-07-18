package com.jaydp.samplepayment.injectionbase.components;

import com.jaydp.samplepayment.MyApp;
import com.jaydp.samplepayment.injectionbase.modules.AppModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * The God Component of the application
 * All Singletons that live through out the life of the application live here
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {

  void inject(MyApp app);
}
