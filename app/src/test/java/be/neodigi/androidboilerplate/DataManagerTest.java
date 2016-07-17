package be.neodigi.androidboilerplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import be.neodigi.androidboilerplate.data.DataManager;
import be.neodigi.androidboilerplate.data.local.DatabaseHelper;
import be.neodigi.androidboilerplate.data.local.PreferencesHelper;
import be.neodigi.androidboilerplate.data.model.User;
import be.neodigi.androidboilerplate.data.remote.RestService;
import be.neodigi.androidboilerplate.test.common.TestDataFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock RestService mMockRestService;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockRestService, mMockPreferencesHelper,
                mMockDatabaseHelper);
    }

    @Test
    public void syncUsersEmitsValues() {
        List<User> users = Arrays.asList(TestDataFactory.makeUser("r1"),
                TestDataFactory.makeUser("r2"));
        stubSyncUsersHelperCalls(users);

        TestSubscriber<User> result = new TestSubscriber<>();
        mDataManager.syncUsers().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(users);
    }

    @Test
    public void syncUsersCallsApiAndDatabase() {
        List<User> users = Arrays.asList(TestDataFactory.makeUser("r1"),
                TestDataFactory.makeUser("r2"));
        stubSyncUsersHelperCalls(users);

        mDataManager.syncUsers().subscribe();
        // Verify right calls to helper methods
        verify(mMockRestService).getUsers();
        verify(mMockDatabaseHelper).setUsers(users);
    }

    @Test
    public void syncUsersDoesNotCallDatabaseWhenApiFails() {
        when(mMockRestService.getUsers())
                .thenReturn(Observable.<List<User>>error(new RuntimeException()));

        mDataManager.syncUsers().subscribe(new TestSubscriber<User>());
        // Verify right calls to helper methods
        verify(mMockRestService).getUsers();
        verify(mMockDatabaseHelper, never()).setUsers(anyListOf(User.class));
    }

    private void stubSyncUsersHelperCalls(List<User> users) {
        // Stub calls to the rest service and database helper.
        when(mMockRestService.getUsers())
                .thenReturn(Observable.just(users));
        when(mMockDatabaseHelper.setUsers(users))
                .thenReturn(Observable.from(users));
    }

}
