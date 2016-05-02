package be.neodigi.androidboilerplate.injection.component;

import be.neodigi.androidboilerplate.injection.PerActivity;
import be.neodigi.androidboilerplate.injection.module.ActivityModule;
import be.neodigi.androidboilerplate.ui.main.MainActivity;
import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
