package be.neodigi.androidboilerplate.injection.component;

import be.neodigi.androidboilerplate.injection.scope.PerActivity;
import be.neodigi.androidboilerplate.injection.module.ActivityModule;
import be.neodigi.androidboilerplate.ui.main.MainActivity;
import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
