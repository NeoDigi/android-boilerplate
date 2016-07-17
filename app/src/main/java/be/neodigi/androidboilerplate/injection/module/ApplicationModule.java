package be.neodigi.androidboilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import be.neodigi.androidboilerplate.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(@ApplicationContext Context context) {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder(context);
        builder.name("boilerplate.realm");
        return builder.build();
    }

    @Provides
    Realm provideRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }
}
