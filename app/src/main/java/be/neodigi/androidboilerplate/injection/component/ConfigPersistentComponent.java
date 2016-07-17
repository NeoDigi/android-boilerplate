package be.neodigi.androidboilerplate.injection.component;

import be.neodigi.androidboilerplate.injection.ConfigPersistent;
import be.neodigi.androidboilerplate.injection.module.ActivityModule;
import be.neodigi.androidboilerplate.ui.base.BaseActivity;
import dagger.Component;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}
