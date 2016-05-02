package be.neodigi.androidboilerplate.data.local;

import android.content.Context;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import be.neodigi.androidboilerplate.data.model.User;
import be.neodigi.androidboilerplate.injection.ApplicationContext;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import timber.log.Timber;

@Singleton
public class DatabaseHelper {

    private final Context mContext;
    private RealmConfiguration mRealmConfiguration;

    @Inject
    public DatabaseHelper(@ApplicationContext Context context) {
        mContext = context;
    }

    public void setup() {
        if (mRealmConfiguration == null) {
            mRealmConfiguration = new RealmConfiguration.Builder(mContext)
                    .name("boilerplate.realm")
                    .build();
            Realm.setDefaultConfiguration(mRealmConfiguration);
        }
    }

    public Realm getRealmInstance() {
        return Realm.getInstance(mRealmConfiguration);
    }

    public Observable<User> setUsers(final Collection<User> newUsers) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    Realm realm = getRealmInstance();
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(newUsers);
                    realm.commitTransaction();
                } catch (Exception e) {
                    Timber.e(e, "There was an error while adding in Realm.");
                    subscriber.onError(e);
                }
            }
        });
    }

    public Observable<List<User>> getUsers() {
        return getRealmInstance().where(User.class).findAllAsync().asObservable()
                .map(new Func1<RealmResults<User>, List<User>>() {
                    @Override
                    public List<User> call(RealmResults<User> users) {
                        return users;
                    }
                });
    }
}
