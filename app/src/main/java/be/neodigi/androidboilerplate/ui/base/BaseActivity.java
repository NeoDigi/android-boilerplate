package be.neodigi.androidboilerplate.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.neodigi.androidboilerplate.BoilerplateApplication;
import be.neodigi.androidboilerplate.injection.component.ActivityComponent;
import be.neodigi.androidboilerplate.injection.component.DaggerActivityComponent;
import be.neodigi.androidboilerplate.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
