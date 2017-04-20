package yenilab.co.apistartup.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.onesignal.OneSignal;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yenilab.co.apistartup.fragment.FeedFragment;
import yenilab.co.apistartup.fragment.SettingFragment;
import yenilab.co.apistartup.model.Setting;
import yenilab.co.apistartup.util.ApiErrorUtil;
import yenilab.co.apistartup.R;
import yenilab.co.apistartup.api.ApiClientProvider;
import yenilab.co.apistartup.model.APIError;
import yenilab.co.apistartup.model.Device;
import yenilab.co.apistartup.model.OAuth;

public class MainActivity extends AppCompatActivity implements OneSignal.IdsAvailableHandler,
        Callback<Void>, BottomNavigationView.OnNavigationItemSelectedListener {

    private ConstraintLayout mContainer;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Snackbar snackbar;

    public static void start(Context context) {

        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Check user is logged in.
        OAuth oAuth = OAuth.get();
        if (oAuth == null) {
            LoginActivity.start(MainActivity.this);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainer = (ConstraintLayout) findViewById(R.id.container);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fragment = new FeedFragment();
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();

        //To check Device id added to onesignal
        OneSignal.idsAvailable(this);
    }

    @Override
    public void idsAvailable(String userId, String registrationId) {

        if (registrationId != null) {
            Device device = new Device(registrationId, Device.PLATFORM);
            ApiClientProvider.getInstance()
                    .addDevice(device)
                    .enqueue(this);
        } else {
            snackbar = Snackbar.make(mContainer, "Google Registration Id:\nCould not subscribe for push", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

        if (response.isSuccessful()) {
            snackbar = Snackbar.make(mContainer, " Device added to backend successfully.", Snackbar.LENGTH_LONG);
        } else {
            APIError error = ApiErrorUtil.parseError(response);
            snackbar = Snackbar.make(mContainer, error.getMessage(), Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }

    @Override
    public void onFailure(Call<Void> call, Throwable throwable) {

        snackbar = Snackbar.make(mContainer, "Device couldn't added to backend", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new FeedFragment();
                break;
            case R.id.navigation_notifications:
                fragment = new SettingFragment();
                break;
            default:
                fragment = new FeedFragment();
                break;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
        return true;
    }
}
