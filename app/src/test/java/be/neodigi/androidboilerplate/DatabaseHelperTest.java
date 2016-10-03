package be.neodigi.androidboilerplate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import be.neodigi.androidboilerplate.data.model.User;
import io.realm.Realm;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Unit tests integration with Realm using Robolectric
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
@PowerMockIgnore({"org.mockito.*"})
@PrepareForTest({Realm.class})
public class DatabaseHelperTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();
    private Realm mMockRealm;

    @Before
    public void setupRealm() {
        mockStatic(Realm.class);

        Realm mockRealm = PowerMockito.mock(Realm.class);

        when(Realm.getDefaultInstance()).thenReturn(mockRealm);

        mMockRealm = mockRealm;
    }

    @Test
    public void shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), is(mMockRealm));
    }

    @Test
    public void shouldBeAbleToMockRealmMethods() {
        when(mMockRealm.isAutoRefresh()).thenReturn(true);
        assertThat(mMockRealm.isAutoRefresh(), is(true));

        when(mMockRealm.isAutoRefresh()).thenReturn(false);
        assertThat(mMockRealm.isAutoRefresh(), is(false));
    }

    @Test
    public void shouldBeAbleToCreateRealmObject() {
        User user = new User();
        when(mMockRealm.createObject(User.class)).thenReturn(user);

        User output = mMockRealm.createObject(User.class);

        assertThat(output, is(user));
    }
}
