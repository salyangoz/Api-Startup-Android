package io.salyangoz.apistartup.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rey.material.widget.Switch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import io.salyangoz.apistartup.R;
import io.salyangoz.apistartup.api.ApiClientProvider;
import io.salyangoz.apistartup.model.APIError;
import io.salyangoz.apistartup.model.Setting;
import io.salyangoz.apistartup.model.response.SettingResponse;
import io.salyangoz.apistartup.util.ApiErrorUtil;

public class SettingFragment extends Fragment implements Switch.OnCheckedChangeListener, Callback<Void> {

    private Switch sNotification;
    private Snackbar snackbar;
    private LinearLayout mContainer;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance(String param1, String param2) {

        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this io.salyangoz.apistartup.fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        mContainer = (LinearLayout) v.findViewById(R.id.container);
        sNotification = (Switch) v.findViewById(R.id.swt_notification);
        sNotification.setOnCheckedChangeListener(this);

        ApiClientProvider.getInstance()
                .settings()
                .enqueue(settingResponseCallback);

        return v;
    }

    @Override
    public void onCheckedChanged(Switch view, boolean checked) {

        Setting setting = new Setting(checked);
        ApiClientProvider.getInstance()
                .changeSetting(setting).enqueue(this);
    }

    //Change Setting Callback
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

        if (response.isSuccessful()) {
            snackbar = Snackbar.make(mContainer, "Settings changed successfully.", Snackbar.LENGTH_LONG);
        } else {
            APIError error = ApiErrorUtil.parseError(response);
            snackbar = Snackbar.make(mContainer, error.getMessage(), Snackbar.LENGTH_LONG);
        }
        snackbar.show();
    }

    @Override
    public void onFailure(Call<Void> call, Throwable throwable) {

        snackbar = Snackbar.make(mContainer, "Problem occurred while changing settings." + throwable.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //Get Setting Callback
    private Callback<SettingResponse> settingResponseCallback = new Callback<SettingResponse>() {

        @Override
        public void onResponse(Call<SettingResponse> call, Response<SettingResponse> response) {

            if (response.isSuccessful()) {
                boolean notification = response.body().getSetting().isNotificationOn();
                sNotification.setCheckedImmediately(notification);
            } else {
                APIError error = ApiErrorUtil.parseError(response);
                snackbar = Snackbar.make(mContainer, error.getMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }

        @Override
        public void onFailure(Call<SettingResponse> call, Throwable throwable) {

            Snackbar snackbar = Snackbar.make(mContainer, "Notification is : " + throwable.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    };
}
