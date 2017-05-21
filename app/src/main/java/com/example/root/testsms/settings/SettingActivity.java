package com.example.root.testsms.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.root.testsms.R;

/**
 * Created by root on 19/5/17.
 */

public class SettingActivity extends PreferenceActivity {


    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.setting_layout);
        
    }
}
