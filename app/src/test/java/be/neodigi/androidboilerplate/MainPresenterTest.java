package be.neodigi.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import be.neodigi.androidboilerplate.data.DataManager;
import be.neodigi.androidboilerplate.data.model.User;
import be.neodigi.androidboilerplate.test.common.TestDataFactory;
import be.neodigi.androidboilerplate.ui.main.MainMvpView;
import be.neodigi.androidboilerplate.ui.main.MainPresenter;
import be.neodigi.androidboilerplate.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mMockMainMvpView;
    @Mock DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadUsersReturnsUsers() {
        List<User> users = TestDataFactory.makeListUsers(10);
        doReturn(Observable.just(users))
                .when(mMockDataManager)
                .getUsers();

        mMainPresenter.loadUsers();
        verify(mMockMainMvpView).showUsers(users);
        verify(mMockMainMvpView, never()).showUsersEmpty();
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadUsersReturnsEmptyList() {
        doReturn(Observable.just(Collections.emptyList()))
                .when(mMockDataManager)
                .getUsers();

        mMainPresenter.loadUsers();
        verify(mMockMainMvpView).showUsersEmpty();
        verify(mMockMainMvpView, never()).showUsers(anyListOf(User.class));
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadUsersFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(mMockDataManager)
                .getUsers();

        mMainPresenter.loadUsers();
        verify(mMockMainMvpView).showError();
        verify(mMockMainMvpView, never()).showUsersEmpty();
        verify(mMockMainMvpView, never()).showUsers(anyListOf(User.class));
    }
}
