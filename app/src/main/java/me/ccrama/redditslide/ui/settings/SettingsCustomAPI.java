package me.ccrama.redditslide.ui.settings;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Collections;

import me.ccrama.redditslide.Activities.BaseActivityAnim;
import me.ccrama.redditslide.R;
import me.ccrama.redditslide.SettingValues;

public class SettingsCustomAPI extends BaseActivityAnim {


    EditText client_id;
    EditText redirect_url;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyColorTheme();
        setContentView(R.layout.activity_settings_customapi);
        setupAppBar(R.id.toolbar, R.string.settings_customapi, true, true);


        client_id = (EditText) findViewById(R.id.client_id);
        redirect_url = (EditText) findViewById(R.id.redirect_url);

        client_id.setText(SettingValues.customapiClient);
        redirect_url.setText(SettingValues.customapiRedirect);

        if (SettingValues.customapiClient.isEmpty() && SettingValues.customapiRedirect.isEmpty()) {
            (findViewById(R.id.remove_customapi)).setEnabled(false);
        }
        findViewById(R.id.remove_customapi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SettingValues.customapiRedirect.isEmpty()) {

                    new AlertDialog.Builder(SettingsCustomAPI.this)
                            .setTitle(R.string.settings_customapi_delete)
                            .setPositiveButton(R.string.btn_yes, (dialog, which) -> {
                                SettingValues.customapiClient = "";
                                SettingValues.customapiRedirect = "";
                                SharedPreferences.Editor e = SettingValues.prefs.edit();

                                e.putString(SettingValues.CUSTOMAPI_CLIENT, SettingValues.customapiClient);
                                e.putString(SettingValues.CUSTOMAPI_REDIRECT, SettingValues.customapiRedirect);
                                e.apply();
                                client_id.setText(SettingValues.customapiClient);
                                redirect_url.setText(SettingValues.customapiRedirect);
                            })
                            .setNegativeButton(R.string.btn_no, null)
                            .show();
                }
            }
        });

        findViewById(R.id.save_customapi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingValues.customapiClient = client_id.getText().toString();
                SettingValues.customapiRedirect = redirect_url.getText().toString();

                SharedPreferences.Editor e = SettingValues.prefs.edit();
                e.putString(SettingValues.CUSTOMAPI_CLIENT, SettingValues.customapiClient);
                e.putString(SettingValues.CUSTOMAPI_REDIRECT, SettingValues.customapiRedirect);
                e.apply();
                (findViewById(R.id.remove_customapi)).setEnabled(true);
            }
        });
    }
}
