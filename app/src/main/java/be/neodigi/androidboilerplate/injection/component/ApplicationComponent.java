package be.neodigi.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import be.neodigi.androidboilerplate.data.DataManager;
import be.neodigi.androidboilerplate.data.SyncService;
import be.neodigi.androidboilerplate.data.local.DatabaseHelper;
import be.neodigi.androidboilerplate.data.local.PreferencesHelper;
import be.neodigi.androidboilerplate.data.remote.RestService;
import be.neodigi.androidboilerplate.injection.ApplicationContext;
import be.neodigi.androidboilerplate.injection.module.ApplicationModule;
import be.neodigi.androidboilerplate.injection.module.RestModule;
import dagger.Component;
import io.realm.Realm;

@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RestService restService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    Realm realm();
    DataManager dataManager();
    Bus eventBus();
}
