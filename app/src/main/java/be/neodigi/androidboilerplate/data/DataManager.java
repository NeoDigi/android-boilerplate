package be.neodigi.androidboilerplate.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import be.neodigi.androidboilerplate.data.local.DatabaseHelper;
import be.neodigi.androidboilerplate.data.local.PreferencesHelper;
import be.neodigi.androidboilerplate.data.model.User;
import be.neodigi.androidboilerplate.data.remote.RestService;
import be.neodigi.androidboilerplate.util.EventPosterHelper;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final RestService mRestService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(RestService restService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mRestService = restService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<User> syncUsers() {
        return mRestService.getUsers()
                .concatMap(new Func1<List<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(List<User> users) {
                        return mDatabaseHelper.setUsers(users);
                    }
                });
    }

    public Observable<List<User>> getUsers() {
        return mDatabaseHelper.getUsers().distinct();
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                mEventPoster.postEventSafely(event);
            }
        };
    }

}
