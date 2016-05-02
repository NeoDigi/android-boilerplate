package be.neodigi.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import be.neodigi.androidboilerplate.injection.component.ApplicationComponent;
import be.neodigi.androidboilerplate.test.common.injection.module.ApplicationTestModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
